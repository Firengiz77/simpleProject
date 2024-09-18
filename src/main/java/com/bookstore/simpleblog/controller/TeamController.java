package com.bookstore.simpleblog.controller;

import com.bookstore.simpleblog.dto.TeamDto;
import com.bookstore.simpleblog.dto.request.TeamRequest;
import com.bookstore.simpleblog.service.TeamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/team")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    @GetMapping
    public List<TeamDto> getTeam() {
        return teamService.getTeam();
    }

    @GetMapping("/{id}")
    public TeamDto getTeamById(@PathVariable Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping
    public TeamDto createTeam(TeamRequest teamRequest)  {
        return teamService.createTeam(teamRequest);
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable Long id,
                           TeamRequest teamRequest) throws IOException {
        teamService.updateTeam(id,teamRequest);
    }

    @DeleteMapping("/{id}")
    public HttpStatus deleteTeam(@PathVariable Long id) {
     return  teamService.deleteTeam(id);
    }
}
