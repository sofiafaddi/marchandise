package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Livraison;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.LivraisonEntity;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
import fr.univlyon1.tiw1.tp3.service.LivraisonService;
import fr.univlyon1.tiw1.tp3.service.MarchandiseService;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import fr.univlyon1.tiw1.tp3.service.exception.MissingParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/29/17.
 *
 * Livraison Controller
 * ====================
 * This controller allows the request cross-origin. It has the CRUD methods and one service for
 * the reception of delivery.
 */
@RestController
@RequestMapping("/livraison")
public class LivraisonController {
    private final EntrepotService entrepotService;
    private final MarchandiseService marchandiseService;
    private final LivraisonService livraisonService;

    /**
     * Instead of use injection by field, we'll use by constructor.
     *
     * @param entrepotService the warehouse service.
     * @param marchandiseService the ware service.
     * @param livraisonService the delivery service.
     */
    @Autowired
    public LivraisonController(EntrepotService entrepotService, MarchandiseService marchandiseService, LivraisonService livraisonService) {
        this.entrepotService = entrepotService;
        this.marchandiseService = marchandiseService;
        this.livraisonService = livraisonService;
    }

    /**
     * Retrieve all the registered deliveries.
     * @return the list of deliveries.
     */
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Livraison>> getAll() {
        try {
            return new ResponseEntity<>(livraisonService.getAll(), HttpStatus.OK);
        } catch (OperationFailedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve the delivery entity by id
     *
     * @param id the delivery identifier
     * @return the delivery entity
     * @throws DataNotFoundException if it doesn't exists any delivery with this identifier.
     */
    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Livraison> get(@PathVariable int id) throws DataNotFoundException {
        Livraison livraison;
        try {
            livraison = livraisonService.getById(id);
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(livraison, HttpStatus.OK);
    }

    /**
     * Create a new delivery entity
     *
     * @param livraisonEntity the delivery entity
     * @return the same entity with identifier.
     * @throws OperationFailedException if it's not possible create a new delivery entity.
     */
    @CrossOrigin
    @PostMapping
    @ResponseBody
    @Transactional
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Livraison> post(@RequestBody LivraisonEntity livraisonEntity) throws OperationFailedException {
        Livraison livraison;

        try {
            Marchandise marchandise = marchandiseService.getByRef(livraisonEntity.getRefMarchandise());
            Entrepot entrepot = entrepotService.getByNom(livraisonEntity.getNomEntrepot());
            livraison = entrepotService.creeLivraison(entrepot, marchandise, livraisonEntity.getMagasin(), livraisonEntity.getQuantite(),
                    livraisonEntity.getDatePrevue());
        } catch (OperationFailedException e) {
            throw e;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(livraison, HttpStatus.CREATED);
    }

    /**
     * Register the deliver of the new delivery entity.
     *
     * @param livraisonEntity the delivery entity.
     * @return nothing but the status line.
     * @throws OperationFailedException if it's not possible register the deliver.
     */
    @CrossOrigin
    @PostMapping("/livrer")
    @ResponseBody
    @Transactional
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity livrer(@RequestBody LivraisonEntity livraisonEntity) throws OperationFailedException {

        if (livraisonEntity.getDateEffectuee() == null)
            throw new MissingParameterException("Le paramètre date effectuée est requis.");

        try {
            entrepotService.livrer(livraisonEntity, livraisonEntity.getDateEffectuee());
        } catch (OperationFailedException e) {
            throw e;
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }


    /**
     * Delete the delivery entity by identifier
     *
     * @param id the identifier of the delivery
     * @return nothing but the status line
     * @throws OperationFailedException if it's not possible remove the entity.
     * @throws DataNotFoundException if it doesn't exists any delivery with this identifier.
     */
    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    @ExceptionHandler({DataNotFoundException.class, OperationFailedException.class})
    public ResponseEntity delete(@PathVariable(value = "id") int id) throws OperationFailedException, DataNotFoundException {
        livraisonService.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
