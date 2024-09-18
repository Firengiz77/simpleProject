package com.bookstore.simpleblog.service;

import com.bookstore.simpleblog.dto.TeamDto;
import com.bookstore.simpleblog.dto.request.TeamRequest;
import com.bookstore.simpleblog.exceptions.TeamNotFoundException;
import com.bookstore.simpleblog.mapper.TeamMapper;
import com.bookstore.simpleblog.model.Image;
import com.bookstore.simpleblog.model.Team;
import com.bookstore.simpleblog.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {
    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;
    private final ImageService imageService;

    public List<TeamDto> getTeam() {
        return teamMapper.toDto(teamRepository.findAll());
    }

    public TeamDto getTeamById(Long id) {
        return teamMapper.toDto(teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id)));
    }

    public TeamDto createTeam(TeamRequest teamRequest) {
        Image image1 = imageService.create(teamRequest.getImage());
        Team team = Team.builder()
                .name(teamRequest.getName())
                .image(image1)
                .description(teamRequest.getDescription())
                .profession(teamRequest.getProfession())
                .slug(teamRequest.getSlug())
                .build();

        return teamMapper.toDto(teamRepository.save(team));
    }

    public void updateTeam(Long id, TeamRequest teamRequest) throws IOException {
        Team team = teamRepository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
        team.setName(teamRequest.getName());
        team.setDescription(teamRequest.getDescription());
        team.setProfession(teamRequest.getProfession());
        team.setSlug(teamRequest.getSlug());
        team.setImage(imageService.update(team.getImage().getId(),teamRequest.getImage()));
        teamRepository.save(team);
    }

    public HttpStatus deleteTeam(Long id) {
        if(teamRepository.existsById(id)) {
            teamRepository.deleteById(id);
            return HttpStatus.OK;
        }
        else{
            return HttpStatus.NOT_FOUND;
        }
    }
}
