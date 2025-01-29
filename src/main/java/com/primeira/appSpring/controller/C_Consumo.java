package com.primeira.appSpring.controller;

import com.primeira.appSpring.model.M_Produto;
import com.primeira.appSpring.repository.R_Produto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class C_Consumo {

    @GetMapping("/consumo")
    public String getConsumo(HttpSession session, Model model) {
        if (session.getAttribute("usuario") != null) {
            model.addAttribute("usuario", session.getAttribute("usuario"));
        }
        return "consumo/consumo";
    }
    @Autowired
    private R_Produto r_produto;
    @GetMapping("/produto/{codBarras}")
    @ResponseBody
    public M_Produto produtoPeloCodigoDeBarras(@PathVariable String codBarras) {
        M_Produto produto = r_produto.findByCodBarras(codBarras);
        return produto; // Retorna o produto ou null se não encontrado
    }
    @GetMapping("/produtos")
    @ResponseBody
    public List<M_Produto> buscarProdutosPorDescricao(@RequestParam String descricao) {
        return r_produto.findByDescricao(descricao);
    }


}
