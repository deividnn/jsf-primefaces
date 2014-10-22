/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import entidade.Cliente;
import entidade.Info;
import entidade.Nota;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import util.Util;

/**
 *
 * @author Deivid
 */
@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private Nota nota;
    private Info info;
    private Cliente cliente;

    private List<Cliente> lista;//lista de clientes adicionados pelo usuario
    private StringBuilder xml;//string que armazenara o xml
    private String xmlFinal;//atributo usado para mostrar o xml

    //inicializa os objetos
    @PostConstruct
    public void init() {
        this.nota = new Nota();
        this.nota.setInfo(new Info());
        this.cliente = new Cliente();
        this.lista = new LinkedList<>();
        this.xml = new StringBuilder();
    }

    /**
     * adiciona um cliente na lista de clientes
     */
    public void adicionarCliente() {
        this.lista.add(this.cliente);
        Util.criarMensagem("cliente adicionado");//cria mensagem de cliente adicionado
        resetarForm();//reseta o formulario
        this.cliente = new Cliente();
    }

    /**
     * gera um xml apartir de dados do formulario
     */
    public void gerarXML() {
        //limpa as strings que armazena o xml
        this.xml = new StringBuilder();
        this.xmlFinal = new String();

        if (!this.lista.isEmpty()) {//se a lista de cliente nao for vazia

            this.nota.setClientes(this.lista);//atribui a lista de cliente na nota

            this.xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append("\n");//tag inicial do xml

            XStream xstream = new XStream();//cria um objeto do xstream
            String[] formats = {"yyyy-MM-dd"};//formato da data
            xstream.registerConverter(new DateConverter("yyyy-MM-dd", formats));//registra o conversor de datas
            xstream.alias("nota", Nota.class);//gera a primeria tag nota
            xstream.aliasField("info", Nota.class, "info");//gera a tag info dentro de nota
            xstream.aliasField("clientes", Nota.class, "clientes");//gera uma tag de clientes na nota
            xstream.alias("cliente", Cliente.class);//gera tags cliente dentro de clientes
            String xml2 = xstream.toXML(this.nota);//gera o xml

            this.xml.append(xml2);//coloca o xml dentro da string
            Util.executarJavascript("PF('dlgxml').show();");

        }
        this.xmlFinal = this.xml.toString();//string que sera mostrada na pagina
    }

    /**
     *
     * @param event de upload do primefaces
     * @throws IOException
     */
    public void lerXML(FileUploadEvent event) throws IOException {
        //inicializa novamente os objetos
        this.nota = new Nota();
        this.xml = new StringBuilder();
        this.lista = new LinkedList<>();

        try {
            //le dados do arquivo
            try (BufferedReader br = new BufferedReader(new InputStreamReader(event.getFile().getInputstream(), "UTF-8"))) {
                String line;
                //escreve cada linha na string xml
                while ((line = br.readLine()) != null) {
                    this.xml.append(line);
                    System.out.println(line);

                }
            }
        } catch (Exception e) {
            System.out.println(e);
            Util.criarMensagem("erro ao ler o xml");
        }
        try {
            XStream xstream = new XStream();//cria um objeto do xstream
            String[] formats = {"yyyy-MM-dd"};//formato da data
            xstream.registerConverter(new DateConverter("yyyy-MM-dd", formats));//registra o conversor de datas
            xstream.alias("nota", Nota.class);//pega a primeria tag nota
            xstream.aliasField("info", Nota.class, "info");//pega tag info dentro de nota
            xstream.aliasField("clientes", Nota.class, "clientes");//pega uma tag de clientes da nota
            xstream.alias("cliente", Cliente.class);//pegas as tags cliente dentro de clientes
            Nota resultado = (Nota) xstream.fromXML(this.xml.toString());//pega dados xml e passa para o objeto nota

            this.nota = resultado;

            //atribui todos os clientes para a lista de cliente
            for (Cliente c : this.nota.getClientes()) {
                this.lista.add(c);
            }

            if (!this.lista.isEmpty()) {
                Util.criarMensagem("xml importado");
                Util.executarJavascript("PF('lerxml').hide();");
            } else {
                Util.criarMensagem("erro ao ler o xml");
            }
        } catch (Exception e) {
            System.out.println(e);
            Util.criarMensagem("erro ao ler o xml");
        }

    }

    /**
     * resetar formulario da pagina
     */
    public void resetarForm() {
        Util.resetarFormulario("form");
    }

    public Nota getNota() {
        return nota;
    }

    public void setNota(Nota nota) {
        this.nota = nota;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getLista() {
        return lista;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public StringBuilder getXml() {
        return xml;
    }

    public void setXml(StringBuilder xml) {
        this.xml = xml;
    }

    public String getXmlFinal() {
        return xmlFinal;
    }

    public void setXmlFinal(String xmlFinal) {
        this.xmlFinal = xmlFinal;
    }

}
