package com.davidesdras.bazar.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
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
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/lote")
public class LoteController {
  @Autowired
  private HttpServletRequest request;
  @Autowired
  private HttpSession session;
  @Autowired
  private RepositoryFacade facade;

  private String __msg = null;

  @RequestMapping({ "/todos", "" })
  public String todos(
    Model m, 
    @PathParam("donatarioId") String donatarioIdStr,
    @PathParam("fiscalizadorId") String fiscalizadorIdStr) {
    try {
      m.addAttribute("donatarios", facade.readAllDonatarios());
      m.addAttribute("fiscalizadores", facade.readAllFiscalizador());

      if ((donatarioIdStr == null || donatarioIdStr.isBlank()) && (fiscalizadorIdStr == null || fiscalizadorIdStr.isBlank())) {
        session.setAttribute("lotes", facade.readAllLote());
      } else if ((donatarioIdStr == null || donatarioIdStr.isBlank()) && fiscalizadorIdStr != null && !fiscalizadorIdStr.isBlank()) {
        int fiscalizadorId = Integer.parseInt(fiscalizadorIdStr);
        session.setAttribute("lotes", facade.filterLoteByFiscalizadorId(fiscalizadorId));
      } else if (donatarioIdStr != null && !donatarioIdStr.isBlank() && (fiscalizadorIdStr == null || fiscalizadorIdStr.isBlank())) {
        int donatarioId = Integer.parseInt(donatarioIdStr);
        session.setAttribute("lotes", facade.filterLoteByDonatarioId(donatarioId));
      } else {
        int donatarioId = Integer.parseInt(donatarioIdStr);
        int fiscalizadorId = Integer.parseInt(fiscalizadorIdStr);
        session.setAttribute("lotes", facade.filterLoteByDonatarioIdAndFiscalizadorId(donatarioId, fiscalizadorId));
      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
      this.__msg = null;
    }
    return "lote/todos";
  }

  @GetMapping("/{id}")
  public String getLoteById(Model m, @PathVariable int id) {
    try {
      Lote o = facade.readLote(id);
      if (o == null) {
        return "lote/nao-encontrado";
      }
      m.addAttribute("lote", o);
      return "lote/detalhe";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/delete/{id}")
  public String deleteLoteById(Model m, @PathVariable int id) {
    try {
      Lote o = facade.readLote(id);
      if (o == null) {
        return "lote/nao-encontrado";
      }
      facade.delete(o);
      return "lote/apagado";
    } catch (ClassNotFoundException e) {
      m.addAttribute("mensagem", "Erro interno. Tente novamente mais tarde.");
      return "erro";
    } catch (SQLException e) {
      m.addAttribute("mensagem", e.getMessage());
      return "erro";
    }
  }

  @GetMapping("/cadastrar")
  public String getFormCadastro(Model model) {
    try {
      model.addAttribute("fiscalizadores", facade.readAllFiscalizador());
      model.addAttribute("donatarios", facade.readAllDonatarios());
      LoteCadastroDTO loteCadastro = new LoteCadastroDTO();
      model.addAttribute("loteCadastro", loteCadastro);
      return "lote/cadastrar";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/cadastrar")
  public String postCadastro(@ModelAttribute LoteCadastroDTO loteCadastroDTO)
      throws ClassNotFoundException, SQLException {
    Lote lote = new Lote();
    lote.setDataEntrega(loteCadastroDTO.getDataEntrega());
    lote.setObservacao(loteCadastroDTO.getObservacao());

    OrgaoDonatario d = new OrgaoDonatario();
    d.setId(loteCadastroDTO.getDonatarioId());
    lote.setDonatario(d);

    OrgaoFiscalizador f = new OrgaoFiscalizador();
    f.setId(loteCadastroDTO.getFiscalizadorId());
    lote.setFiscalizador(f);

    List<Produto> produtos = new ArrayList<>();

    for (ProdutoDTO pDto : loteCadastroDTO.getDoacoes()) {
      Produto p = new Produto();
      p.setNome(pDto.getNome());
      p.setDescricao(pDto.getDescricao());
      produtos.add(p);
    }

    lote.setDoacoes(produtos);
    facade.create(lote);
    return "sucesso";
  }

  @GetMapping("/editar/{id}")
  public String editarLote(Model model, @PathVariable int id) {
    try {
      model.addAttribute("fiscalizadores", facade.readAllFiscalizador());
      model.addAttribute("donatarios", facade.readAllDonatarios());
      Lote lote = facade.readLote(id);
      model.addAttribute("lote", lote);
      return "lote/editar";
    } catch (ClassNotFoundException | SQLException e) {
      return "erro";
    }
  }

  @PostMapping("/editar")
  public String editarLoteCadastro(@ModelAttribute LoteEditarDTO loteEditarDTO) throws ClassNotFoundException, SQLException {
    Lote lote = new Lote();
    lote.setId(loteEditarDTO.getId());

    lote.setDataEntrega(loteEditarDTO.getDataEntrega());
    lote.setObservacao(loteEditarDTO.getObservacao());

    OrgaoDonatario d = new OrgaoDonatario();
    d.setId(loteEditarDTO.getDonatarioId());
    lote.setDonatario(d);

    OrgaoFiscalizador f = new OrgaoFiscalizador();
    f.setId(loteEditarDTO.getFiscalizadorId());
    lote.setFiscalizador(f);
    facade.update(lote);
    return "sucesso";
  }
}
