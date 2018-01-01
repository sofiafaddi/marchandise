package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Entrepot;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.EntrepotEntity;
import fr.univlyon1.tiw1.tp3.service.EntrepotService;
import fr.univlyon1.tiw1.tp3.service.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author Amaia Nazábal
 * @version 1.0
 * @since 1.0 11/15/17.
 *
 * Entrepot controller
 * ===================
 * This controller allows the request cross-origin. It has the CRUD methods and one service for
 * the reception of provision.
 *
 */
@RestController
@RequestMapping("/entrepot")
public class EntrepotController {

    private final EntrepotService entrepotService;

    /**
     * Instead of use injection by field, we'll use by constructor.
     *
     * @param entrepotService the entrepot service.
     */
    @Autowired
    public EntrepotController(EntrepotService entrepotService) {
        this.entrepotService = entrepotService;
    }

    /**
     * Retrieve all the registered warehouses.
     *
     * @return the list of warehouses.
     */
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Entrepot>> getAll() {
        try {
            return new ResponseEntity<>(entrepotService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve the warehouse entity by identifier.
     *
     * @param nom the warehouse names.
     * @return the warehouses entity
     * @throws DataNotFoundException if it doesn't exists any ware house with this id.
     */
    @CrossOrigin
    @GetMapping("/{nomEntrepot}")
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Entrepot> get(@PathVariable(value="nomEntrepot") String nom) throws DataNotFoundException {
        Entrepot entrepot;
        entrepot = entrepotService.getByName(nom);

        return new ResponseEntity<>(entrepot, HttpStatus.OK);
    }

    /**
     * Create a new warehouse.
     *
     * @param entrepot the warehouse entity.
     * @return the same entity.
     */
    @CrossOrigin
    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Entrepot> post(@RequestBody EntrepotEntity entrepot) {
        try {
            entrepotService.createOrUpdate(entrepot);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(entrepot, HttpStatus.CREATED);
    }

    /**
     * Update the warehouse entity.
     *
     * @param nom the name of the entity, which is the primary key.
     * @param entrepot the warehouse entity.
     * @return the same entity.
     */
    @CrossOrigin
    @PutMapping(value = "/{nom}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Entrepot> put(@PathVariable("nom") String nom,
                                        @RequestBody EntrepotEntity entrepot) {
        try {
            entrepotService.createOrUpdate(entrepot);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(entrepot, HttpStatus.OK);
    }

    /**
     * Delete the warehouse entity.
     *
     * @param nom the name of the warehouse.
     * @return nothing but the status line.
     * @throws DataNotFoundException if it doesn't exists any warehouse with the name sended.
     * @throws OperationFailedException if it is not possible delete the entity in the database.
     */
    @CrossOrigin
    @DeleteMapping(value = "/{nom}")
    @ResponseBody
    @Transactional
    @ExceptionHandler({DataNotFoundException.class, OperationFailedException.class})
    public ResponseEntity delete(@PathVariable("nom") String nom) throws DataNotFoundException, OperationFailedException {
        entrepotService.remove(nom);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
