package com.example.respringboot.converters;

import com.example.respringboot.commands.CompanyCommand;
import com.example.respringboot.commands.ProfitCenterCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.ProfitCenter;
import io.micrometer.common.lang.Nullable;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfitToProfitCommand implements Converter<ProfitCenter, ProfitCenterCommand> {
    private final ProjectToProjectCommand projectConverter;
    private final BuildingToBuildingCommand buildingConverter;

    public ProfitToProfitCommand(ProjectToProjectCommand projectConverter, BuildingToBuildingCommand buildingConverter) {
        this.projectConverter = projectConverter;
        this.buildingConverter = buildingConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public ProfitCenterCommand convert(ProfitCenter source) {
        if (source == null) {
            return null;
        }

        final ProfitCenterCommand profitCenterCommand = new ProfitCenterCommand();
        profitCenterCommand.setId(source.getId());
        profitCenterCommand.setProfitId(source.getProfitId());
        profitCenterCommand.setProfitDescr(source.getProfitDescr());
        if (source.getProjects() != null && source.getProjects().size() > 0){
            source.getProjects()
                    .forEach(project -> profitCenterCommand.getProjectCommands().add(projectConverter.convert(project)));
        }
        if (source.getBuildings() != null && source.getBuildings().size() > 0){
            source.getBuildings()
                    .forEach(building -> profitCenterCommand.getBuildingCommands().add(buildingConverter.convert(building)));
        }
        return profitCenterCommand;
    }
}
