package lt.traveladvisor.mvp.advisor.rest;

import lt.traveladvisor.mvp.advisor.model.*;
import lt.traveladvisor.mvp.advisor.rest.request.AdviseRequest;
import lt.traveladvisor.mvp.advisor.rest.response.AdviseResponse;
import lt.traveladvisor.mvp.advisor.rest.response.mapper.AdviseResponseMapper;
import lt.traveladvisor.mvp.advisor.service.AdvisorService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/trip")
public class TripAdvisorController {

    private final AdvisorService advisorService;

    public TripAdvisorController(AdvisorService advisorService) {
        this.advisorService = advisorService;
    }

    @PostMapping("/advise")
    public AdviseResponse advise(@RequestBody @Valid AdviseRequest request) {
        Advise advise = advisorService.generateAdvise(request);

        return AdviseResponseMapper.from(advise);
    }
}
