package br.com.opon.opon_api.repository.specification;

import br.com.opon.opon_api.model.Servico;
import br.com.opon.opon_api.model.enums.CategoriaServico;
import br.com.opon.opon_api.model.enums.StatusServico;
import org.springframework.data.jpa.domain.Specification;

public class ServicoSpecification {

    public static Specification<Servico> porStatus(StatusServico status){
        return(root, query, criteriaBiulder) ->
                criteriaBiulder.equal(root.get("statusServico"), status);
    }

    public static Specification<Servico> porCategoria(CategoriaServico categoria){
        return(root, query, criteriaBiulder) ->
                criteriaBiulder.equal(root.get("categoria"), categoria);
    }

    public static Specification<Servico> porClienteId(Integer idCliente){
        return(root, query, criteriaBiulder) ->
                criteriaBiulder.equal(root.get("cliente").get("idCliente"), idCliente);
    }
}
