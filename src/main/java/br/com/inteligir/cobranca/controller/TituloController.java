package br.com.inteligir.cobranca.controller;

import java.math.BigDecimal;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.inteligir.cobranca.model.StatusTitulo;
import br.com.inteligir.cobranca.model.Titulo;
import br.com.inteligir.cobranca.repository.TituloRepository;

@Controller
public class TituloController {

    @Autowired
    private TituloRepository tituloRepository;

    /**
     * @return
     * Lista todos os títulos cadastrados no banco de dados
     */
    @GetMapping("/titulos")
    public ModelAndView listaTitulos(){

        ModelAndView modelAndView = new ModelAndView("listagemTitulos");
        modelAndView.addObject("titulos", tituloRepository.findAll());

        return modelAndView;
    }

    /**
     * @return
     * Direciona para a tela de cadastro de titulo
     */
    @GetMapping("/titulos/cadastroTitulo")
    public ModelAndView cadastroTitulo(){

        ModelAndView modelAndView = new ModelAndView("cadastroTitulo");
        modelAndView.addObject("titulo", new Titulo());
        
        return modelAndView;
    }

    /**
     * @param descricao
     * @param dataVencimento
     * @param valor
     * @param statusTitulo
     * @return
     * Salva um novo titulo e redireciona o usuário para a listagem de títulos
     */
    @PostMapping("/titulos")
    public String novoTitulo(String descricao, Date dataVencimento, BigDecimal valor, StatusTitulo statusTitulo){
                

        Titulo novoTitulo = new Titulo();
        novoTitulo.setDescricao(descricao);
        novoTitulo.setDataVencimento(dataVencimento);
        novoTitulo.setValor(valor);
        novoTitulo.setStatusTitulo(statusTitulo);

        tituloRepository.save(novoTitulo);

        return "redirect:/titulos";
    }

    /**
     * @param codigo
     * @return
     * Pesquisa o titulo pelo Codigo através do método encontrarTitulo()
     */
    @GetMapping("/titulos/{codigo}/editar")
    private ModelAndView retornaTitulo(@PathVariable Long codigo){

        Titulo titulo = encontrarTitulo(codigo);

        ModelAndView mv = new ModelAndView("cadastroTitulo");
        mv.addObject("titulo", titulo);

        return mv;
    }

    /**
     * @param titulo
     * @return
     * Retorna o titulo setado pelo Codigo
     * Em seguida são aplicadas as etapas de atualização do tituloAtualizado
     * o método save do TituloRepository salva/atualiza o titulo com os dados 
     * do novo objeto tituloAtualizado
     * por fim redireciona o usuário para a listagem de títulos
     */
    @PutMapping(path="/titulos/{codigo}")
    public String atualizarTitulo(Titulo titulo){

        Titulo tituloAtualizado = encontrarTitulo(titulo.getCodigo());
        
        tituloAtualizado.setDescricao(titulo.getDescricao());
        tituloAtualizado.setDataVencimento(titulo.getDataVencimento());
        tituloAtualizado.setValor(titulo.getValor());
        tituloAtualizado.setStatusTitulo(titulo.getStatusTitulo());

        tituloRepository.save(tituloAtualizado);

        return "redirect:/titulos";
    }


    /*---------------Métodos Auxiliares--------------- */

    /**
     * @param codigo
     * @return
     * Encontra o titulo atraves do método findById do TituloRepository
     */
    @GetMapping("/titulos/{codigo}")
    Titulo encontrarTitulo(@PathVariable Long codigo){

        return tituloRepository.findById(codigo).orElseThrow();
    }

    

    
}
