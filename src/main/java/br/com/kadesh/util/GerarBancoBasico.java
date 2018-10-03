package br.com.kadesh.util;

import br.com.kadesh.dao.impl.CondPagDao;
import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.FamiliaDao;
import br.com.kadesh.dao.impl.GrupoDao;
import br.com.kadesh.dao.impl.LinhaDao;
import br.com.kadesh.dao.impl.TipoPedidoDao;
import br.com.kadesh.dao.impl.UsuarioDao;
import br.com.kadesh.model.CondicaoPagamento;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.PermissaoEnum;
import br.com.kadesh.model.TipoPedido;
import br.com.kadesh.model.Usuario;

public class GerarBancoBasico {

    public static void main(String[] args) {
        CondPagDao condPagDao = new CondPagDao();
        TipoPedidoDao tipoPedidoDao = new TipoPedidoDao();
        EstadoDao estadoDao = new EstadoDao();
        LinhaDao linhaDao = new LinhaDao();
        FamiliaDao familiaDao = new FamiliaDao();
        GrupoDao grupoDao = new GrupoDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        
        Usuario usuario = new Usuario(0, "Administrador", "adm@kadesh.com.br", "adm", "1234", true, PermissaoEnum.ADMIN);
        usuarioDao.create(usuario);
        

        Estado estado = new Estado(0, "Parana", "PR");
        Estado estado2 = new Estado(0, "Santa Catarina", "SC");
        Estado estado3 = new Estado(0, "Rio Grande Do Sul", "RS");
        Estado estado4 = new Estado(0, "Minas Gerais", "MG");
        Estado estado5 = new Estado(0, "São Paulo", "SP");
        Estado estado6 = new Estado(0, "Rio de Janeiro", "RJ");

        estadoDao.create(estado);
        estadoDao.create(estado2);
        estadoDao.create(estado3);
        estadoDao.create(estado4);
        estadoDao.create(estado5);
        estadoDao.create(estado6);


        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(0, "30/60/90", 1);
        CondicaoPagamento condicaoPagamento2 = new CondicaoPagamento(0, "15/45/50", 1);
        CondicaoPagamento condicaoPagamento3 = new CondicaoPagamento(0, "20/40", 1);

        condPagDao.create(condicaoPagamento);
        condPagDao.create(condicaoPagamento2);
        condPagDao.create(condicaoPagamento3);

        TipoPedido tipoPedido = new TipoPedido(0, "Pedido de Venda", "Venda");
        TipoPedido tipoPedido2 = new TipoPedido(0, "Cotação", "Cotação");
        TipoPedido tipoPedido3 = new TipoPedido(0, "Pedido de Amostra", "Amostra");

        tipoPedidoDao.create(tipoPedido);
        tipoPedidoDao.create(tipoPedido2);
        tipoPedidoDao.create(tipoPedido3);

    }

}
