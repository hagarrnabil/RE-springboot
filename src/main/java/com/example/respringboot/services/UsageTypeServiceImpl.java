package com.example.respringboot.services;

import com.example.respringboot.commands.UsageTypeCommand;
import com.example.respringboot.converters.UsageTypeCommandToUsageType;
import com.example.respringboot.converters.UsageTypeToUsageTypeCommand;
import com.example.respringboot.model.Company;
import com.example.respringboot.model.UsageType;
import com.example.respringboot.repositories.UsageTypeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class UsageTypeServiceImpl implements UsageTypeSevice{
    private final UsageTypeToUsageTypeCommand usageTypeToUsageTypeCommand;
    private final UsageTypeCommandToUsageType usageTypeCommandToUsageType;
    private final UsageTypeRepository usageTypeRepository;

    public UsageTypeServiceImpl(UsageTypeToUsageTypeCommand usageTypeToUsageTypeCommand, UsageTypeCommandToUsageType usageTypeCommandToUsageType, UsageTypeRepository usageTypeRepository) {
        this.usageTypeToUsageTypeCommand = usageTypeToUsageTypeCommand;
        this.usageTypeCommandToUsageType = usageTypeCommandToUsageType;
        this.usageTypeRepository = usageTypeRepository;
    }

    @Override
    @Transactional
    public Set<UsageTypeCommand> getUsageTypeCommands() {
        return StreamSupport.stream(usageTypeRepository.findAll()
                        .spliterator(), false)
                .map(usageTypeToUsageTypeCommand::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public UsageType findById(Long l) {
        Optional<UsageType> usageTypeOptional = usageTypeRepository.findById(l);

        if (!usageTypeOptional.isPresent()) {
            throw new RuntimeException("Usage Type Not Found!");
        }

        return usageTypeOptional.get();
    }

    @Override
    public void deleteById(Long idToDelete) {
        usageTypeRepository.deleteById(idToDelete);
    }

    @Override
    @Transactional
    public UsageTypeCommand saveUsageTypeCommand(UsageTypeCommand command) {
        UsageType detachedUsageType = usageTypeCommandToUsageType.convert(command);
        UsageType savedUsageType = usageTypeRepository.save(detachedUsageType);
        log.debug("Saved Usage Type Id:" + savedUsageType.getUsageTypeCode());
        return usageTypeToUsageTypeCommand.convert(savedUsageType);
    }

    @Override
    @Transactional
    public UsageTypeCommand findUsageTypeCommandById(Long l) {
        return usageTypeToUsageTypeCommand.convert(findById(l));
    }
}
