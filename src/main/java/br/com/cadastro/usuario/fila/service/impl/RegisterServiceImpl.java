package br.com.cadastro.usuario.fila.service.impl;

import br.com.cadastro.usuario.fila.config.exceptions.UserPermissionsException;
import br.com.cadastro.usuario.fila.mappers.ClientMapper;
import br.com.cadastro.usuario.fila.service.RegisterService;
import br.com.register.commons.domain.v1.dto.ClientDTO;
import br.com.register.commons.domain.v1.entity.ClientEntity;
import br.com.register.commons.domain.v1.repositories.ClientRepository;
import br.com.register.commons.domain.v1.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientRepository clientRepository;

    private final BCryptPasswordEncoder B_CRIPT = new BCryptPasswordEncoder();

    @Async("asyncThreadPool")
    @Override
    public void register(ClientDTO clientDTO) {
        this.isAdmin();

        try {
            if (clientRepository.findByEmail(clientDTO.getEmail()).isPresent()) {
                throw new RuntimeException("Já existe um usuário com o mesmo e-mail cadastrado.");
            }

            ClientEntity clienteEntity = this.clientMapper.fromDTO(clientDTO);

//            clienteEntity.setCidade(cidadeService.buscarPorNomeAndSiglaEstado(clienteDTO.getCidade().getNome(), clienteDTO.getCidade().getEstado().getSigla()));
//            clienteEntity.setSenha(B_CRIPT.encode(clienteDTO.getSenha()));

            this.clientRepository.saveAndFlush(clienteEntity);

        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar novo cliente: " + e.getMessage());
        }
    }

    public void isAdmin() {
        if (!this.getAuthenticatedDetails().getIsAdmin()) {
            throw new UserPermissionsException("Usuário sem permissão para executar a operação.");
        }
    }

    public static UserAuthentication getAuthenticated() {
        return (UserAuthentication) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public ClientEntity getAuthenticatedDetails() {
        try {
            return this.clientRepository.findById(getAuthenticated().getID()).get();

        } catch (Exception e) {
            return null;
        }
    }

}

