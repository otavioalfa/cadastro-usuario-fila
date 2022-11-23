package br.com.cadastro.usuario.fila.mappers;

import br.com.register.commons.domain.v1.dto.StateDTO;
import br.com.register.commons.domain.v1.entity.StateEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface StateMapper {

    StateDTO toDTO(StateEntity estadoEntity);

    StateEntity fromDTO(StateDTO estadoDTO);
}