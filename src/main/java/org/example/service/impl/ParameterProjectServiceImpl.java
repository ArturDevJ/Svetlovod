package org.example.service.impl;

import org.example.dto.request.ParameterProjectRequest;
import org.example.service.ParameterProjectService;

import java.util.ArrayList;
import java.util.List;


public class ParameterProjectServiceImpl implements ParameterProjectService {
    @Override
    public ParameterProjectRequest save(ParameterProjectRequest parameterProjectRequest) {

        return null;
    }
    @Override
    public List<ParameterProjectRequest> findAll() {
        return new ArrayList<>();
    }

    @Override
    public ParameterProjectRequest getParameterProjectRequestById(Long id) {
        return null;
    }

    @Override
    public void deleteParameterProjectRequestById(Long id) {
    }
}
