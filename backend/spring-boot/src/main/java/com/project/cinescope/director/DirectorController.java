package com.project.cinescope.director;

import com.project.cinescope.actor.Actor;
import com.project.cinescope.director.request.DirectorRequestDto;
import com.project.cinescope.director.response.DirectorResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/api/directors")
public class DirectorController {
    private final DirectorService directorService;
    private final RequestMappingHandlerMapping handlerMapping;

    public DirectorController(DirectorService directorService, RequestMappingHandlerMapping handlerMapping) {
        this.directorService = directorService;
        this.handlerMapping = handlerMapping;
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        Class<?> myClass = this.getClass();
        List<Map<String, Object>> routes = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = handlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
            HandlerMethod handlerMethod = entry.getValue();

            if (handlerMethod.getBeanType().equals(myClass)) {
                RequestMappingInfo mappingInfo = entry.getKey();

                Set<String> urls = mappingInfo.getDirectPaths();
                System.out.println(urls);
//                Map<String, Object> routeInfo = new LinkedHashMap<>();
            }
        }
        return ResponseEntity.ok("OK");
    }

    @GetMapping
    public ResponseEntity<List<DirectorResponseDto>> getAll() {
        List<DirectorResponseDto> responseDtoList = directorService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(responseDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> getById(
            @PathVariable Long id
    ) {
        DirectorResponseDto responseDto = directorService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping
    public ResponseEntity<DirectorResponseDto> post(
            @RequestBody @Valid DirectorRequestDto requestDto
    ) {
        DirectorResponseDto responseDto = directorService.post(requestDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseDto.id())
                .toUri();

        return ResponseEntity.created(uri).body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DirectorResponseDto> delete(
            @PathVariable Long id
    ) {
        directorService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
