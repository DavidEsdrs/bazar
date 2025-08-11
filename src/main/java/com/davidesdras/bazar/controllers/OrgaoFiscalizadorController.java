package com.davidesdras.bazar.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidesdras.bazar.model.entities.OrgaoDonatario;
import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;
import com.davidesdras.bazar.model.repositories.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orgao-fiscalizador")
public class OrgaoFiscalizadorController {
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @Autowired
  private RepositoryFacade facade;

  private String __msg = null;

  @RequestMapping({ "/*", "" })
  public String mainPage(Model m) {
    try {
      session.setAttribute("fiscalizadores", facade.readAllFiscalizador());
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      this.__msg = null;
    }
    return "fiscalizador/todos";
  }

  @GetMapping("/{id}")
  public String getOrgaoFiscalizadorById(Model m, @PathVariable int id) {
    try {
      OrgaoFiscalizador o = facade.readOrgaoFiscalizador(id);
      if (o == null) {
        return "fiscalizador/nao-encontrado";
      }
      m.addAttribute("fiscalizador", o);
      return "fiscalizador/detalhe";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/delete/{id}")
  public String deleteOrgaoFiscalizadorById(Model m, @PathVariable int id) {
    try {
      OrgaoFiscalizador o = facade.readOrgaoFiscalizador(id);
      if (o == null) {
        return "fiscalizador/nao-encontrado";
      }
      facade.delete(o);
      return "fiscalizador/apagado";
    } catch (ClassNotFoundException e) {
      m.addAttribute("mensagem", "Erro interno. Tente novamente mais tarde.");
      return "erro";
    } catch (SQLException e) {
      m.addAttribute("mensagem", "Não é possível apagar um fiscalizador que tem lotes relacionados");
      return "erro";
    }
  }

  @GetMapping("/cadastrar")
  public String getCadastro(Model m) throws ClassNotFoundException, SQLException {
    m.addAttribute("fiscalizador", new OrgaoFiscalizador());
    return "fiscalizador/cadastrar";
  }

  @PostMapping("/cadastrar")
  public String postCadastro(OrgaoFiscalizador fiscalizador) throws ClassNotFoundException, SQLException {
    facade.create(fiscalizador);
    return "sucesso";
  }

  @GetMapping("/editar/{id}")
  public String editarFiscalizador(Model model, @PathVariable int id) {
    try {
      OrgaoFiscalizador fiscalizador = facade.readOrgaoFiscalizador(id);
      model.addAttribute("fiscalizador", fiscalizador);
      return "fiscalizador/editar";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/editar")
  public String editarFiscalizadorCadastro(@ModelAttribute OrgaoFiscalizador fiscalizador)
      throws ClassNotFoundException, SQLException {
    OrgaoFiscalizador d = new OrgaoFiscalizador();
    d.setId(fiscalizador.getId());
    d.setNome(fiscalizador.getNome());
    d.setDescricao(fiscalizador.getDescricao());
    facade.update(d);
    return "sucesso";
  }
}
