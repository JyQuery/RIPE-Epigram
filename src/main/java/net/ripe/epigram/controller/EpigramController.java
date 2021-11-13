package net.ripe.epigram.controller;

import net.ripe.epigram.model.Epigram;
import net.ripe.epigram.model.EpigramRepository;
import net.ripe.epigram.response.Response;
import net.ripe.epigram.response.ResponseFail;
import net.ripe.epigram.response.ResponseSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
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
     * @param request
     * @return Response
     */
    @GetMapping
    public Response getAll(HttpServletRequest request) {
        return new ResponseSuccess(request.getRequestURI(), epigramRepository.findAll());
    }

    /**
     * Return a random epigram or by id
     * @param id
     * @param request
     * @return Response
     */
    @GetMapping("/{id}")
    public Response getOne(@PathVariable String id, HttpServletRequest request) {
        // Return a random epigram
        // get all epigrams and retrieve a random one
        if (id.equals("random")) {
            ArrayList<Epigram> arr = (ArrayList<Epigram>) epigramRepository.findAll();
            Random rd = new Random();
            return new ResponseSuccess(request.getRequestURI(), arr.get(rd.nextInt(arr.size())));
        }

        // Return an epigram by id
        else {
            try {
                // check check variable
                Long epId = Long.parseLong(id);
                Optional<Epigram> data = epigramRepository.findById(epId);
                if (data.isPresent())
                    return new ResponseSuccess(request.getRequestURI(), data);

                return new ResponseFail(request.getRequestURI(), 404, "Epigram not exist");
            } catch (NumberFormatException e) {
                return new ResponseFail(request.getRequestURI(), 400, "Invalid parameter");
            }
        }
    }

    /**
     * Create a new epigram
     * @param content
     * @param request
     * @return Response
     */
    @PostMapping
    public Response post(@RequestParam(value="content") String content, HttpServletRequest request) {
        Epigram e = new Epigram();
        e.setContent(content);
        e.setCreated_at(new Timestamp(System.currentTimeMillis()));
        epigramRepository.save(e);
        return new ResponseSuccess(request.getRequestURI(), e);
    }

    /**
     * Update an epigram
     * @param id
     * @param content
     * @param request
     * @return Response
     */
    @PutMapping("/{id}")
    public Response put(@PathVariable Long id, @RequestParam(value="content") String content, HttpServletRequest request) {
        // check if id exist
        Optional<Epigram> ep = epigramRepository.findById(id);
        if (ep.isPresent()) {
            Epigram e = ep.get();
            e.setContent(content);
            e.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            epigramRepository.save(e);
            return new ResponseSuccess(request.getRequestURI(), e);
        }
        return new ResponseFail(request.getRequestURI(), 404, "Epigram not exist");
    }

    /**
     * Delete an epigram
     * @param id
     * @param request
     * @return Response
     */
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id, HttpServletRequest request) {
        // check if id exist
        if (!epigramRepository.existsById(id))
            return new ResponseFail(request.getRequestURI(), 404, "Epigram not exist");
        epigramRepository.deleteById(id);
        return new ResponseSuccess(request.getRequestURI(),id);
    }
}
