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
import br.com.kadesh.model.Familia;
import br.com.kadesh.model.Grupo;
import br.com.kadesh.model.Linha;
import br.com.kadesh.model.PermissaoEnum;
import br.com.kadesh.model.StatusEnum;
import br.com.kadesh.model.TipoPedido;
import br.com.kadesh.model.Usuario;

public class GerarBancoBasico {

    public static void main(String[] args) throws HashGenerationException {
        CondPagDao condPagDao = new CondPagDao();
        TipoPedidoDao tipoPedidoDao = new TipoPedidoDao();
        EstadoDao estadoDao = new EstadoDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        LinhaDao linhaDao = new LinhaDao();
        FamiliaDao familiaDao = new FamiliaDao();
        GrupoDao grupoDao = new GrupoDao();

        Usuario usuario = new Usuario(0, "Administrador", "adm@kadesh.com.br", "adm", Digest.hashString("1234", "SHA-256"), StatusEnum.ATIVO, PermissaoEnum.ADMIN);
        usuarioDao.create(usuario);

//        Estado estado = new Estado(0, "Acre", "AC");
//        Estado estado2 = new Estado(0, "Alagoas", "AL");
//        Estado estado3 = new Estado(0, "Amapá", "AP");
//        Estado estado4 = new Estado(0, "Amazonas", "AM");
//        Estado estado5 = new Estado(0, "Bahia", "BA");
//        Estado estado6 = new Estado(0, "Ceará", "CE");
//        Estado estado7 = new Estado(0, "Distrito Federal", "DF");
//        Estado estado8 = new Estado(0, "Espírito Santo", "ES");
//        Estado estado9 = new Estado(0, "Goiás", "GO");
//        Estado estado10 = new Estado(0, "Maranhão", "MA");
//        Estado estado11 = new Estado(0, "Mato Grosso", "MT");
//        Estado estado12 = new Estado(0, "Mato Grosso do Sul", "MS");
//        Estado estado13 = new Estado(0, "Minas Gerais", "MG");
//        Estado estado14 = new Estado(0, "Pará", "PA");
//        Estado estado15 = new Estado(0, "Paraíba", "PB");
//        Estado estado16 = new Estado(0, "Paraná", "PR");
//        Estado estado17 = new Estado(0, "Pernambuco", "PE");
//        Estado estado18 = new Estado(0, "Piauí", "PI");
//        Estado estado19 = new Estado(0, "Rio de Janeiro", "RJ");
//        Estado estado20 = new Estado(0, "Rio Grande do Norte", "RN");
//        Estado estado21 = new Estado(0, "Rio Grande do Sul", "RS");
//        Estado estado22 = new Estado(0, "Rondônia", "RO");
//        Estado estado23 = new Estado(0, "Roraima", "RR");
//        Estado estado24 = new Estado(0, "Santa Catarina", "SC");
//        Estado estado25 = new Estado(0, "São Paulo", "SP");
//        Estado estado26 = new Estado(0, "Sergipe", "SE");
//        Estado estado27 = new Estado(0, "Tocantins", "TO");
//
//        estadoDao.create(estado);
//        estadoDao.create(estado2);
//        estadoDao.create(estado3);
//        estadoDao.create(estado4);
//        estadoDao.create(estado5);
//        estadoDao.create(estado6);
//        estadoDao.create(estado7);
//        estadoDao.create(estado8);
//        estadoDao.create(estado9);
//        estadoDao.create(estado10);
//        estadoDao.create(estado11);
//        estadoDao.create(estado12);
//        estadoDao.create(estado13);
//        estadoDao.create(estado14);
//        estadoDao.create(estado15);
//        estadoDao.create(estado16);
//        estadoDao.create(estado17);
//        estadoDao.create(estado18);
//        estadoDao.create(estado19);
//        estadoDao.create(estado20);
//        estadoDao.create(estado21);
//        estadoDao.create(estado22);
//        estadoDao.create(estado23);
//        estadoDao.create(estado24);
//        estadoDao.create(estado25);
//        estadoDao.create(estado26);
//        estadoDao.create(estado27);
//
//        CondicaoPagamento condicaoPagamento = new CondicaoPagamento(0, "30/60/90", 1);
//        CondicaoPagamento condicaoPagamento2 = new CondicaoPagamento(0, "15/45/50", 1);
//        CondicaoPagamento condicaoPagamento3 = new CondicaoPagamento(0, "20/40", 1);
//
//        condPagDao.create(condicaoPagamento);
//        condPagDao.create(condicaoPagamento2);
//        condPagDao.create(condicaoPagamento3);
//
//        TipoPedido tipoPedido = new TipoPedido(0, "Pedido de Venda", "Venda");
//        TipoPedido tipoPedido2 = new TipoPedido(0, "Cotação", "Cotação");
//        TipoPedido tipoPedido3 = new TipoPedido(0, "Pedido de Amostra", "Amostra");
//
//        tipoPedidoDao.create(tipoPedido);
//        tipoPedidoDao.create(tipoPedido2);
//        tipoPedidoDao.create(tipoPedido3);
//
//        Linha linha = new Linha(0, "Adventure", "Adventure", "ADV");
//        Linha linha2 = new Linha(0, "Premium", "Premium", "PRM");
//        Linha linha3 = new Linha(0, "Impacto", "Impacto", "IMP");
//
//        linhaDao.create(linha);
//        linhaDao.create(linha2);
//        linhaDao.create(linha3);
//
//        Grupo grupo = new Grupo(0, "Kadesh", "Kadesh", "KSH");
//        Grupo grupo2 = new Grupo(0, "Imbiseg", "Imbiseg", "IMB");
//        Grupo grupo3 = new Grupo(0, "Bemfort", "Bemfort", "BFT");
//
//        grupoDao.create(grupo);
//        grupoDao.create(grupo2);
//        grupoDao.create(grupo3);
//
//        Familia familia = new Familia(0, "Botina", "Botina", "BOT");
//        Familia familia2 = new Familia(0, "Sapato", "Sapato", "SAP");
//        Familia familia3 = new Familia(0, "Tênis", "Tênis", "TNS");
//
//        familiaDao.create(familia);
//        familiaDao.create(familia2);
//        familiaDao.create(familia3);

    }

}
