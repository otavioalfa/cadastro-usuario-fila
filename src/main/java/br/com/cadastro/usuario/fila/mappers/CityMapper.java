package br.com.cadastro.usuario.fila.mappers;

import br.com.register.commons.domain.v1.dto.CityDTO;
import br.com.register.commons.domain.v1.entity.CityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface CityMapper {

    CityDTO toDTO(CityEntity cidadeEntity);

    CityEntity fromDTO(CityDTO cidadeDTO);
}