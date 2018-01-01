package fr.univlyon1.tiw1.tp3.controller;

import fr.univlyon1.tiw1.metier.spec.Marchandise;
import fr.univlyon1.tiw1.metier.spec.OperationFailedException;
import fr.univlyon1.tiw1.tp3.modele.MarchandiseEntity;
import fr.univlyon1.tiw1.tp3.service.MarchandiseService;
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
 * @since 1.0 11/28/17.
 *
 * Marchandise Controller
 * ======================
 * This controller allows the request cross-origin. It has the CRUD methods and one service for
 * the wares.
 */
@RestController
@RequestMapping("/marchandise")
public class MarchandiseController {

    private MarchandiseService marchandiseService;

    @Autowired
    public MarchandiseController(MarchandiseService marchandiseService) {
        this.marchandiseService = marchandiseService;
    }

    /**
     * Retrieve all the wares registereds.
     * @return the list of wares.
     */
    @CrossOrigin
    @GetMapping
    @ResponseBody
    public ResponseEntity<Collection<Marchandise>> getAll() {
        try {
            return new ResponseEntity<>(marchandiseService.getAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Retrieve the ware entity by reference.
     *
     * @param ref the reference of the ware.
     * @return the ware entity.
     * @throws DataNotFoundException if it doesn't exists any ware entity with this reference.
     */
    @CrossOrigin
    @GetMapping("/{ref}")
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<Marchandise> get(@PathVariable(value="ref") int ref) throws DataNotFoundException {
        Marchandise marchandise;
        try {
            marchandise = marchandiseService.getByReference(ref);
            return new ResponseEntity<>(marchandise, HttpStatus.OK);
        } catch (DataNotFoundException e) {
            throw e;
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Create a new ware entity.
     *
     * @param marchandise the ware entity.
     * @return the same entity
     */
    @CrossOrigin
    @PostMapping
    @ResponseBody
    @Transactional
    public ResponseEntity<Marchandise> post(@RequestBody MarchandiseEntity marchandise) {
        try {
            return new ResponseEntity<>(marchandiseService.createOrUpdate(marchandise), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Updates the ware entity.
     *
     * @param ref the reference of the entity
     * @param marchandise the ware entity.
     * @return the same entity already registered in the database.
     */
    @CrossOrigin
    @PutMapping("/{ref}")
    @ResponseBody
    @Transactional
    public ResponseEntity<Marchandise> put(@PathVariable Integer ref, @RequestBody MarchandiseEntity marchandise) {
        try {
            marchandise.setReference(ref);
            return new ResponseEntity<>(marchandiseService.createOrUpdate(marchandise), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete the ware entity.
     *
     * @param ref the reference of the entity.
     * @return nothing but the status line.
     * @throws OperationFailedException if it's not possible to make the deletion.
     * @throws DataNotFoundException if it doesn't exists any ware with this reference.
     */
    @CrossOrigin
    @DeleteMapping("/{ref}")
    @Transactional
    @ExceptionHandler({DataNotFoundException.class, OperationFailedException.class})
    public ResponseEntity delete(@PathVariable int ref) throws OperationFailedException, DataNotFoundException {
            marchandiseService.remove(ref);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
