package org.example.service;

import org.example.dto.request.ParameterProjectRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ParameterProjectService {
    ParameterProjectRequest save(ParameterProjectRequest parameterProjectRequest);
    List<ParameterProjectRequest> findAll();
    ParameterProjectRequest getParameterProjectRequestById(Long id);
    void deleteParameterProjectRequestById(Long id);
}
