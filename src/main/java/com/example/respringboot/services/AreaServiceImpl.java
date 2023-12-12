package com.example.respringboot.services;

import com.example.respringboot.commands.AreaMasterDetailCommand;
import com.example.respringboot.converters.AreaMasterDetailCommandToAreaMasterDetail;
import com.example.respringboot.converters.AreaMasterDetailToAreaMasterDetailCommand;
import com.example.respringboot.model.AreaMasterDetail;
import com.example.respringboot.model.Company;
import com.example.respringboot.repositories.AreaMasterDetailRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class AreaServiceImpl implements AreaMasterDetailService{
    private final AreaMasterDetailToAreaMasterDetailCommand areaMasterDetailToAreaMasterDetailCommand;
    private final AreaMasterDetailCommandToAreaMasterDetail areaMasterDetailCommandToAreaMasterDetail;
    private final AreaMasterDetailRepository areaMasterDetailRepository;

    public AreaServiceImpl(AreaMasterDetailToAreaMasterDetailCommand areaMasterDetailToAreaMasterDetailCommand,
                           AreaMasterDetailCommandToAreaMasterDetail areaMasterDetailCommandToAreaMasterDetail,
                           AreaMasterDetailRepository areaMasterDetailRepository)
    {
        this.areaMasterDetailToAreaMasterDetailCommand = areaMasterDetailToAreaMasterDetailCommand;
        this.areaMasterDetailCommandToAreaMasterDetail = areaMasterDetailCommandToAreaMasterDetail;
        this.areaMasterDetailRepository = areaMasterDetailRepository;
    }

    @Override
    @Transactional
    public Set<AreaMasterDetailCommand> getAreaCommands() {
        return StreamSupport.stream(areaMasterDetailRepository.findAll()
                        .spliterator(), false)
                .map(areaMasterDetailToAreaMasterDetailCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public AreaMasterDetail findById(Long l) {
        Optional<AreaMasterDetail> areaMasterDetailOptional = areaMasterDetailRepository.findById(l);

        if (!areaMasterDetailOptional.isPresent()) {
            throw new RuntimeException("Area Not Found!");
        }

        return areaMasterDetailOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        areaMasterDetailRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public AreaMasterDetailCommand saveAreaCommand(AreaMasterDetailCommand command) {

        AreaMasterDetail detachedArea= areaMasterDetailCommandToAreaMasterDetail.convert(command);
        AreaMasterDetail savedArea = areaMasterDetailRepository.save(detachedArea);
        log.debug("Saved Area Id:" + savedArea.getAreaCode());
        return areaMasterDetailToAreaMasterDetailCommand.convert(savedArea);

    }

    @Override
    @Transactional
    public AreaMasterDetailCommand findAreaCommandById(Long l) {
        return areaMasterDetailToAreaMasterDetailCommand.convert(findById(l));
    }
}
