package cinema.app.controller;

import cinema.app.dto.request.MovieRequestDto;
import cinema.app.dto.response.MovieResponseDto;
import cinema.app.model.Movie;
import cinema.app.service.MovieService;
import cinema.app.service.mapper.MovieMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @PostMapping("/add")
    public MovieResponseDto add(@RequestBody @Valid MovieRequestDto requestDto) {
        Movie movie = movieService.add(movieMapper.mapToModel(requestDto));
        return movieMapper.mapToDto(movie);
    }

    @GetMapping("/get")
    public List<MovieResponseDto> getAll() {
        return movieService.getAll()
                .stream()
                .map(movieMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
