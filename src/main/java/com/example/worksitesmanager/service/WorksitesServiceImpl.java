package com.example.worksitesmanager.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.example.worksitesmanager.entity.WorksitesRequest;
import com.example.worksitesmanager.model.Worksites;
import com.example.worksitesmanager.repository.WorksitesRepository;

@Service
public class WorksitesServiceImpl implements WorksitesService {
    @Autowired
    private WorksitesRepository worksitesRepository;
    
    @Override
    public Worksites getWorksitesById(Long id) throws Exception {
        Optional<Worksites> worksitesData = worksitesRepository.findById(id);
        if (!worksitesData.isPresent()) {
            throw new NotFoundException();
        }
        return worksitesData.get();
    }

    @Override
    public List<Worksites> getAllWorksites() {
        List<Worksites> worksitesListData = worksitesRepository.findAllOrderByCreatedAt();
        return worksitesListData;
    }

    @Override
    public List<Worksites> getWorksitesByUpdatedBetween(String strFrom, String strTo) {
        List<Worksites> worksitesListData = worksitesRepository.findByUpdatedAtBetweenByCreatedAt(strFrom, strTo);
        return worksitesListData;
    }

    @Override
    public Worksites getMaxIdWorksites() throws Exception {
        Long maxId = worksitesRepository.findMaxId();
        maxId = maxId == null ? 0 : maxId;
        Optional<Worksites> worksitesData = worksitesRepository.findById(maxId);
        if (!worksitesData.isPresent()) {
            throw new NotFoundException();
        }
        return worksitesData.get();
    }

    @Transactional
    @Override
    public Worksites createWorksites(WorksitesRequest worksitesRequest) {
        Worksites worksites = new Worksites(
            null,
            worksitesRequest.getName(),
            worksitesRequest.getSub_name(),
            worksitesRequest.getType(),
            worksitesRequest.getStaff_name(),
            worksitesRequest.getPhoto(),
            worksitesRequest.getAddress(),
            worksitesRequest.getStatus(),
            worksitesRequest.getStart_at(),
            worksitesRequest.getEnd_at(),
            LocalDateTime.now(),
            LocalDateTime.now()
        );
        return worksitesRepository.save(worksites);
    }

    @Transactional
    @Override
    public Worksites updateWorksites(Long id, WorksitesRequest worksitesRequest) throws Exception {
        Optional<Worksites> worksitesData = worksitesRepository.findById(id);
        if (!worksitesData.isPresent()) {
            throw new NotFoundException();
        }
        Worksites worksites = worksitesData.get();
        worksites.setName(worksitesRequest.getName());
        worksites.setSub_name(worksitesRequest.getSub_name());
        worksites.setType(worksitesRequest.getType());
        worksites.setStaff_name(worksitesRequest.getStaff_name());
        worksites.setPhoto(worksitesRequest.getPhoto());
        worksites.setAddress(worksitesRequest.getAddress());
        worksites.setStatus(worksitesRequest.getStatus());
        worksites.setStart_at(worksitesRequest.getStart_at());
        worksites.setEnd_at(worksitesRequest.getEnd_at());
        worksites.setUpdated_at(LocalDateTime.now());
        return worksitesRepository.save(worksites);
    }

    @Transactional
    @Override
    public Worksites deleteWorksites(Long id) throws Exception {
        Optional<Worksites> worksitesData = worksitesRepository.findById(id);
        if (!worksitesData.isPresent()) {
            throw new NotFoundException();
        }
        worksitesRepository.delete(worksitesData.get());
        return worksitesData.get();
    }
}
