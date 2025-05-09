package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criaProdutoComumComOValorIGualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();

        produto.setProdutoNome("PlayStation 5");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("preto");
        cores.add("branco");

        produto.setProdutoCores(cores);
        produto.setProdutoUrlMock("");

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Controle");
        componente.setComponenteQtd(2);

        componentes.add(componente);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("Cabo HDMI");
        segundoComponente.setComponenteQtd(1);

        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;
    }
}
