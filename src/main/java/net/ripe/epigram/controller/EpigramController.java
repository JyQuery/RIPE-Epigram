package net.ripe.epigram.controller;

import net.ripe.epigram.model.Epigram;
import net.ripe.epigram.model.EpigramRepository;
import net.ripe.epigram.response.Response;
import net.ripe.epigram.response.ResponseFail;
import net.ripe.epigram.response.ResponseNotExist;
import net.ripe.epigram.response.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping(value = "/epigram", produces = MediaType.APPLICATION_JSON_VALUE)
public class EpigramController {
    @Autowired
    private EpigramRepository epigramRepository;

    /**
     * Return all epigrams
     * @return Response
     */
    @GetMapping
    public Response getAll() {
        return new ResponseSuccess(epigramRepository.findAll());
    }

    /**
     * Return a random epigram or by id
     * @param id
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getOne(@PathVariable String id) {
        // Return a random epigram
        if (id.equals("random")) {
            // get all epigrams and retrieve a random one
            ArrayList<Epigram> arr = (ArrayList<Epigram>) epigramRepository.findAll();
            Random rd = new Random();
            return new ResponseSuccess(arr.get(rd.nextInt(arr.size())));
        }

        // Return an epigram by id
        try {
            // try to convert variable to Long
            Long epId = Long.parseLong(id);

            // check if the epigram exists
            Optional<Epigram> data = epigramRepository.findById(epId);
            if (data.isPresent())
                return new ResponseSuccess(data);

            // no result with such id
            return new ResponseNotExist();
        } catch (NumberFormatException e) {
            // other values
            return new ResponseFail(400, "Invalid parameter");
        }
    }

    /**
     * Create a new epigram
     * @param content
     * @return Response
     */
    @PostMapping
    public Response post(@RequestParam(value="content") String content) {
        Epigram e = new Epigram();
        e.setContent(content);
        e.setCreated_at(new Timestamp(System.currentTimeMillis()));
        epigramRepository.save(e);
        return new ResponseSuccess(e);
    }

    /**
     * Update an epigram
     * @param id
     * @param content
     * @return Response
     */
    @PutMapping("/{id}")
    public Response put(@PathVariable Long id, @RequestParam(value="content") String content) {
        // check if id exist
        Optional<Epigram> ep = epigramRepository.findById(id);
        if (ep.isPresent()) {
            Epigram e = ep.get();
            e.setContent(content);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            epigramRepository.save(e);
            return new ResponseSuccess(e);
        }
        return new ResponseNotExist();
    }

    /**
     * Delete an epigram
     * @param id
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        // check if id exist
        if (!epigramRepository.existsById(id))
            return new ResponseNotExist();
        epigramRepository.deleteById(id);
        return new ResponseSuccess(id);
    }
}
