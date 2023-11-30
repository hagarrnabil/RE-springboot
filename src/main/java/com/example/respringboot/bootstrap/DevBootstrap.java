package com.example.respringboot.bootstrap;

import com.example.respringboot.model.Building;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.Project;
import com.example.respringboot.model.Unit;
import com.example.respringboot.repositories.BuildingRepository;
import com.example.respringboot.repositories.CompanyRepository;
import com.example.respringboot.repositories.ProjectRepository;
import com.example.respringboot.repositories.UnitRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private CompanyRepository companyRepository;
    private ProjectRepository projectRepository;
    private BuildingRepository buildingRepository;
    private UnitRepository unitRepository;

    public DevBootstrap(CompanyRepository companyRepository, ProjectRepository projectRepository, BuildingRepository buildingRepository, UnitRepository unitRepository) {
        this.companyRepository = companyRepository;
        this.projectRepository = projectRepository;
        this.buildingRepository = buildingRepository;
        this.unitRepository = unitRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }

    private void initData() {

//        Company company = new Company();
//        company.setCompanyCodeID("company1");
//        company.setCompanyCodeDescription("company 1");
//        companyRepository.save(company);
//
//        Project project = new Project();
//        project.setProjectID("project1");
//        project.setProjectDescription("project 1");
//        project.setValidFrom(LocalDate.ofEpochDay(2022 - 02 - 19));
//        project.setCompany(company);
//        company.getProjects().add(project);
//        projectRepository.save(project);
//
//        Building building = new Building();
//        building.setBuildingID("building1");
//        building.setBuildingDescription("building 1");
//        building.setOldNumber("2");
//        building.setValidFrom(LocalDate.ofEpochDay(2023 - 06 - 19));
//        building.setNumberOfFloors(4);
//        building.setProject(project);
//        project.getBuildings().add(building);
//        buildingRepository.save(building);
//
//        Unit unit = new Unit();
//        unit.setUnitKey("unit1");
//        unit.setOldNumber("3");
//        unit.setDescription("unit 1");
//        unit.setBlockingDate(LocalDate.ofEpochDay(2023 - 06 - 15));
//        unit.setBlockingReason("sold");
//        unit.setSalesPhase("pending");
//        unit.setConstructionDate(LocalDate.ofEpochDay(2023 - 05 - 19));
//        unit.setUnitDeliveryDate(LocalDate.ofEpochDay(2023 - 02 - 22));
//        unit.setArea("garden area");
//        unit.setAreaValue(150);
//        unit.setNoOfRooms(5);
//        unit.setPrice(20000);
//        unit.setValidFrom(LocalDate.ofEpochDay(2023 - 01 - 13));
//        unit.setBuilding(building);
//        building.getUnits().add(unit);
//        unitRepository.save(unit);


    }
}
