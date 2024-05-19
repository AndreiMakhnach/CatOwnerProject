package com.project2.controller;


import com.project2.DTO.OwnerDTO;
import com.project2.entity.CatOwner;
import com.project2.repository.OwnerRepo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "MainMethods")
@RestController
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerRepo ownerRepo;

    @Operation(
            summary = "Кладет нового владельца в базу",
            description = "Получает DTO владельца и через билдер собирает и записывает сущность в бд"
    )
    @PostMapping("/api/add")
    public void addCat(@RequestBody OwnerDTO ownerDTO) {
        System.out.println(
                "New row: " + ownerRepo.save(
                        CatOwner.builder()
                                .age(ownerDTO.getAge())
                                .name(ownerDTO.getName())
                                .money(ownerDTO.getMoney())
                                .build())
        );
    }

    @Operation(
            summary = "Выдает список всех владельцев",
            description = "Выдает всех владельцев"
    )
    @SneakyThrows
    @GetMapping("/api/all")
    public List<CatOwner> getAll() {
        return ownerRepo.findAll();
    }

    @Operation(
            summary = "Выдает котейку",
            description = "Выдает информацию про котейку при вызове, например, по id"
    )
    @GetMapping("/api")
    public CatOwner getCat(@RequestParam int id) {
        return ownerRepo.findById(id).orElseThrow();
    }

    @Operation(
            summary = "Удаляет котейку",
            description = "Удаляет информацию по котейке"
    )
    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        ownerRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody CatOwner catOwner) {
        if (!ownerRepo.existsById(catOwner.getId())) {
            return "No such row";
        }
        return ownerRepo.save(catOwner).toString();
    }
}