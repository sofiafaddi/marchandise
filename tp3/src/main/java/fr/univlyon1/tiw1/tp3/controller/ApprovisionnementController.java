package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Approvisionnement;
import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.ApprovisionnementEntity;
import fr.univlyon1.tiw1.tp3.service.ApprovisionnementService;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
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
 * @since 1.0 11/30/17.
 *
 * Approvisionnement controller.
 * =============================
 * This controller allows the request cross-origin. It has the CRUD methods and one service for
 * the reception of provision.
 *
 */
@RequestMapping("/approvisionnement")
@RestController
public class ApprovisionnementController {

    private final EntrepotService entrepotService;
    private final MarchandiseService marchandiseService;
    private final ApprovisionnementService approvisionnementService;

    /**
     * Instead of use injection by field, we'll use by constructor.
     *
     * @param entrepotService the warehouse service.
     * @param marchandiseService the ware service
     * @param approvisionnementService the provision service
     */
    @Autowired
    public ApprovisionnementController(EntrepotService entrepotService, MarchandiseService marchandiseService, ApprovisionnementService approvisionnementService) {
        this.entrepotService = entrepotService;
        this.marchandiseService = marchandiseService;
        this.approvisionnementService = approvisionnementService;
    }

    /**
     * Create a new provision.
     *
     * @param approvisionnementEntity the provision entity
     * @return the provision entity with his id or a fail status line
     * @throws OperationFailedException if it's not possible create the provision.
     */
    @CrossOrigin
    @PostMapping
    @ResponseBody
    @Transactional
    @ResponseStatus(value=HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OperationFailedException.class)
    public ResponseEntity<Approvisionnement> post(@RequestBody ApprovisionnementEntity approvisionnementEntity) throws OperationFailedException {
        Approvisionnement approvisionnement;

        try {
            Marchandise marchandise = marchandiseService.getByRef(approvisionnementEntity.getRefMarchandise());
            Entrepot entrepot = entrepotService.getByNom(approvisionnementEntity.getNomEntrepot());
            approvisionnement = entrepotService.creeApprovisionnement(entrepot, marchandise,
                    approvisionnementEntity.getFournisseur(), approvisionnementEntity.getQuantite(),
                    approvisionnementEntity.getDatePrevue());
        } catch (OperationFailedException e) {
            throw e;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(approvisionnement, HttpStatus.CREATED);
    }


    /**
     * Retrieve all the registered provisions.
     *
     * @return the list of provisions.
     */
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Approvisionnement>> getAll() {
        try {
            return new ResponseEntity<>(approvisionnementService.getAll(), HttpStatus.OK);
        } catch (OperationFailedException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    /**
     * Retrieve one specific provision by id
     *
     * @param id the provision identifier.
     * @return the provision entity.
     * @throws DataNotFoundException if the id doesn't match with any provision.
     */
    @CrossOrigin
    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Approvisionnement> get(@PathVariable int id) throws DataNotFoundException {
        Approvisionnement approvisionnement;
        try {
            approvisionnement = approvisionnementService.getById(id);
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(approvisionnement, HttpStatus.OK);
    }

    /**
     * Receive a new provision.
     *
     * @param approvisionnementEntity the entity of the provision with the effective date.
     * @return only the status line.
     */
    @CrossOrigin
    @PostMapping("/receptionner")
    @Transactional
    @ResponseBody
    public ResponseEntity receptionner(@RequestBody ApprovisionnementEntity approvisionnementEntity) {

        if (approvisionnementEntity.getDateEffectuee() == null)
            throw new MissingParameterException("Le paramètre date effectuée est requis.");

        try {
            entrepotService.receptionner(approvisionnementEntity, approvisionnementEntity.getDateEffectuee());
        } catch (OperationFailedException e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity(HttpStatus.CREATED);
    }

    /**
     * Remove a provision.
     *
     * @param id the provision identifier.
     * @return only the status line.
     * @throws OperationFailedException if it's not possible register the provision reception.
     * @throws DataNotFoundException if it doesn't exists any provision with this id.
     */
    @CrossOrigin
    @DeleteMapping("/{id}")
    @Transactional
    @ExceptionHandler({DataNotFoundException.class, OperationFailedException.class})
    public ResponseEntity delete(@PathVariable(value = "id") int id) throws OperationFailedException, DataNotFoundException {
        approvisionnementService.remove(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
