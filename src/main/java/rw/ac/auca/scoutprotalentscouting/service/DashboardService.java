package rw.ac.auca.scoutprotalentscouting.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.ac.auca.scoutprotalentscouting.model.TrialStatus;
import rw.ac.auca.scoutprotalentscouting.repository.*;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private AthleteRepository athleteRepo;

    @Autowired
    private ScoutRepository scoutRepo;

    @Autowired
    private ZoneRepository zoneRepo;

    @Autowired
    private TrialRepository trialRepo;

    @Autowired
    private SkillScoreRepository skillScoreRepo;


    public Map<String, Long> getSummary(){
        Map<String, Long> summary = new LinkedHashMap<>();
        summary.put("athletes", athleteRepo.count());
        summary.put("scouts", scoutRepo.count());
        summary.put("freeZones", (long) zoneRepo.findFreeZones().size());
        summary.put("trialsToday", trialRepo.countByTrialDate(LocalDate.now()));
        summary.put("invited", trialRepo.countByStatus(TrialStatus.INVITED));
        summary.put("skillScores", skillScoreRepo.count());
        return summary;
    }

}
