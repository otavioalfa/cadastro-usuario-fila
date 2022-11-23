package br.com.cadastro.usuario.fila.mappers;

import br.com.register.commons.domain.v1.dto.ClientDTO;
import br.com.register.commons.domain.v1.entity.ClientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ClientMapper {

    @Mapping(target = "senha", ignore = true)
    ClientDTO toDTO(ClientEntity clienteEntity);

    ClientEntity fromDTO(ClientDTO clienteDTO);
}