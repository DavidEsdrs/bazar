package com.davidesdras.bazar.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.davidesdras.bazar.dto.LoteCadastroDTO;
import com.davidesdras.bazar.dto.LoteEditarDTO;
import com.davidesdras.bazar.dto.ProdutoDTO;
import com.davidesdras.bazar.model.entities.Lote;
import com.davidesdras.bazar.model.entities.OrgaoDonatario;
import com.davidesdras.bazar.model.entities.OrgaoFiscalizador;
import com.davidesdras.bazar.model.entities.Produto;
import com.davidesdras.bazar.model.repositories.RepositoryFacade;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/orgao-donatario")
public class OrgaoDonatarioController {
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
      session.setAttribute("donatarios", facade.readAllDonatarios());
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      this.__msg = null;
    }
    return "donatario/todos";
  }

  @GetMapping("/{id}")
  public String getOrgaoDonatarioById(Model m, @PathVariable int id) {
    try {
      OrgaoDonatario o = facade.readOrgaoDonatario(id);
      if (o == null) {
        return "donatario/nao-encontrado";
      }
      m.addAttribute("donatario", o);
      return "donatario/detalhe";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/delete/{id}")
  public String deleteOrgaoDonatarioById(Model m, @PathVariable int id) {
    try {
      OrgaoDonatario o = facade.readOrgaoDonatario(id);
      if (o == null) {
        return "donatario/nao-encontrado";
      }
      facade.delete(o);
      return "donatario/apagado";
    } catch (ClassNotFoundException e) {
      m.addAttribute("mensagem", "Erro interno. Tente novamente mais tarde.");
      return "erro";
    } catch (SQLException e) {
      m.addAttribute("mensagem", "Não é possível apagar um donatario que tem lotes relacionados");
      return "erro";
    }
  }

  @GetMapping("/cadastrar")
  public String getCadastro(Model m) throws ClassNotFoundException, SQLException {
    m.addAttribute("donatario", new OrgaoDonatario());
    return "donatario/cadastrar";
  }

  @PostMapping("/cadastrar")
  public String postCadastro(OrgaoDonatario donatario) throws ClassNotFoundException, SQLException {
    facade.create(donatario);
    return "sucesso";
  }

  @GetMapping("/editar/{id}")
  public String editarLote(Model model, @PathVariable int id) {
    try {
      OrgaoDonatario donatario = facade.readOrgaoDonatario(id);
      model.addAttribute("donatario", donatario);
      return "donatario/editar";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/editar")
  public String editarLoteCadastro(@ModelAttribute OrgaoDonatario donatario)
      throws ClassNotFoundException, SQLException {
    OrgaoDonatario d = new OrgaoDonatario();
    d.setId(donatario.getId());
    d.setNome(donatario.getNome());
    d.setDescricao(donatario.getDescricao());
    d.setEndereco(donatario.getEndereco());
    d.setTelefone(donatario.getTelefone());
    d.setHorarioFuncionamento(donatario.getHorarioFuncionamento());
    facade.update(d);
    return "sucesso";
  }
}
