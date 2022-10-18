package com.patreonshout.rest.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Interface for endpoints relating to Patreon posts
 */
@RequestMapping(value = "/posts")
@Tag(name = "Post Service",
        description = "Handles all Patreon post related tasks for the database.")
public interface PostImpl {

    /**
     * Endpoint that will get a List of {@link com.patreonshout.beans.PostBean} objects from the database given a specified creator
     *
     * @param creator is the creator of the posts we want to get
     * @return a json body of post objects from a given creator
     */
    @GetMapping("/creator")
    @Operation(summary = "Gets the saved Patreon posts of a specified creator")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "302",
                    description = "Creator posts returned",
                    content = {@Content(mediaType = "application/json")})
    })
    ResponseEntity<?> GetCreatorPosts(@RequestParam(required = true, name = "creator") String creator);

}
