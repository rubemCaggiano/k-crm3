package br.com.kadesh.util;

import br.com.kadesh.dao.impl.ClienteDao;
import br.com.kadesh.dao.impl.CondPagDao;
import br.com.kadesh.dao.impl.EnderecoDao;
import br.com.kadesh.dao.impl.EstadoDao;
import br.com.kadesh.dao.impl.FamiliaDao;
import br.com.kadesh.dao.impl.GrupoDao;
import br.com.kadesh.dao.impl.LinhaDao;
import br.com.kadesh.dao.impl.PedidoDao;
import br.com.kadesh.dao.impl.ProdutoDao;
import br.com.kadesh.dao.impl.TipoPedidoDao;
import br.com.kadesh.dao.impl.TransportadoraDao;
import br.com.kadesh.model.CondicaoPagamento;
import br.com.kadesh.model.Estado;
import br.com.kadesh.model.Familia;
import br.com.kadesh.model.Grupo;
import br.com.kadesh.model.Linha;
import br.com.kadesh.model.TipoPedido;

public class GerarBancoBasico {

    public static void main(String[] args) {
        CondPagDao condPagDao = new CondPagDao();
        TipoPedidoDao tipoPedidoDao = new TipoPedidoDao();
        EstadoDao estadoDao = new EstadoDao();
        LinhaDao linhaDao = new LinhaDao();
        FamiliaDao familiaDao = new FamiliaDao();
        GrupoDao grupoDao = new GrupoDao();

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

        Linha linha = new Linha(0, "Adventure", "Adventure", "ADV");
        Linha linha2 = new Linha(0, "Premium", "Premium", "PRM");
        Linha linha3 = new Linha(0, "Impacto", "Impacto", "IMP");

        linhaDao.create(linha);
        linhaDao.create(linha2);
        linhaDao.create(linha3);

        Grupo grupo = new Grupo(0, "Kadesh", "Kadesh", "KSH");
        Grupo grupo2 = new Grupo(0, "Imbiseg", "Imbiseg", "IMB");
        Grupo grupo3 = new Grupo(0, "Bemfort", "Bemfort", "BFT");

        grupoDao.create(grupo);
        grupoDao.create(grupo2);
        grupoDao.create(grupo3);

        Familia familia = new Familia(0, "Botina", "Botina", "BOT");
        Familia familia2 = new Familia(0, "Sapato", "Sapato", "SAP");
        Familia familia3 = new Familia(0, "Tênis", "Tênis", "TNS");

        familiaDao.create(familia);
        familiaDao.create(familia2);
        familiaDao.create(familia3);

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
