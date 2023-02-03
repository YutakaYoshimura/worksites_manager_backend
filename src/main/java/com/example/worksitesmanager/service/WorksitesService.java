package com.example.worksitesmanager.service;

import java.util.List;
import com.example.worksitesmanager.entity.WorksitesRequest;
import com.example.worksitesmanager.model.Worksites;

public interface WorksitesService {
    public Worksites getWorksitesById(Long id) throws Exception;
    
    public List<Worksites> getAllWorksites();

    public List<Worksites> getWorksitesByUpdatedBetween(String strFrom, String strTo);

    public Worksites getMaxIdWorksites() throws Exception;

    public Worksites createWorksites(WorksitesRequest worksitesRequest);

    public Worksites updateWorksites(Long id, WorksitesRequest worksitesRequest) throws Exception;

    public Worksites deleteWorksites(Long id) throws Exception;
}
