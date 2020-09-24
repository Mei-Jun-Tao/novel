package com.mjt.web;

import com.mjt.pojo.Chapters;
import com.mjt.service.ChaptersService;
import com.mjt.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ChaptersController {

    @Autowired
    ChaptersService chaptersService;

    @PostMapping(value = "/chapteries")
    public Object add(@RequestBody Chapters bena){
        chaptersService.add(bena);
        return bena;
    }

    @DeleteMapping(value = "/chapteries/{id}")
    public Object delete(@PathVariable("id") int id){
        chaptersService.delete(id);
        return null;
    }

    @PutMapping(value = "/chapteries")
    public Object update(@RequestBody Chapters bena){
        chaptersService.update(bena);
        return bena;
    }

    @GetMapping(value = "/chapteries/{id}")
    public Chapters get(@PathVariable("id") int id){
        return chaptersService.get(id);
    }

    @GetMapping(value = "/products/{pid}/chapteries")
    public Page4Navigator<Chapters> list(@PathVariable("pid") int pid, @RequestParam(value = "stary", defaultValue = "0") int stary, @RequestParam(value = "size", defaultValue = "5") int size){
        stary = stary<0?0:stary;

        return chaptersService.list(stary, size, 5, pid);
    }
}
