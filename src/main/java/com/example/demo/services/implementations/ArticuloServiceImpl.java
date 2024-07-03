package com.example.demo.services.implementations;

import com.example.demo.entities.Articulo;
import com.example.demo.entities.Categoria;
import com.example.demo.entities.DTO.ArticuloClienteDto;
import com.example.demo.entities.DTO.ArticuloDto;
import com.example.demo.entities.Unidad_medida;
import com.example.demo.entities.Usuario;
import com.example.demo.entities.transformations.Articulo.ArticuloDtoMapper;
import com.example.demo.entities.transformations.Articulo.ArticuloMapper;
import com.example.demo.repositories.*;
import com.example.demo.services.Interfaces.ArticuloService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
@Service
public class ArticuloServiceImpl implements ArticuloService {

    private CategoriaRepository categoriaRepository;
    private UnidadMedidaRepository unidadMedidaRepository;

    private ArticuloRepository articuloRepository;
    private ArticuloDtoMapper articuloDtoMapper;
    private ArticuloMapper articuloMapper;

    private UsuarioRepository usuarioRepository;

    private UsuarioArticuloRepository usuarioArticuloRepository;
    public ArticuloServiceImpl(CategoriaRepository categoriaRepository, UnidadMedidaRepository unidadMedidaRepository, ArticuloRepository articuloRepository, ArticuloDtoMapper articuloDtoMapper, ArticuloMapper articuloMapper, UsuarioRepository usuarioRepository, UsuarioArticuloRepository usuarioArticuloRepository) {
        this.categoriaRepository = categoriaRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.articuloRepository = articuloRepository;
        this.articuloDtoMapper = articuloDtoMapper;
        this.articuloMapper = articuloMapper;
        this.usuarioRepository = usuarioRepository;
        this.usuarioArticuloRepository = usuarioArticuloRepository;
    }

    @Override
    public ResponseEntity<String> add(ArticuloDto entity) {
        Articulo articulo = new Articulo();
        Categoria categoria = categoriaRepository.getReferenceById(entity.getCategoria());
        articulo.setCategoria(categoria);
        articulo.setNombre(entity.getNombre());
        articulo.setPrecio(entity.getPrecio());
        Unidad_medida unidad_medida = unidadMedidaRepository.getReferenceById(entity.getUnidad_medida());
        articulo.setUnidad_medida(unidad_medida);
        articuloRepository.save(articulo);
        return new ResponseEntity<>("Registro de articulo exitoso", HttpStatus.OK);
    }

    @Override
    public ArticuloDto getById(Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        return articulo.map(articuloDtoMapper).orElseThrow();
    }

    @Override
    public List<ArticuloDto> getAll() {
        List<Articulo> articulos = articuloRepository.findAll();
        return articulos.stream().map(articuloDtoMapper).toList();
    }

    @Override
    public ArticuloDto delete(Long id) {
        Optional<Articulo> articulo = articuloRepository.findById(id);
        articulo.ifPresent(articuloRepository :: delete);
        return articulo.map(articuloDtoMapper).orElseThrow();
    }

    @Override
    public void update(ArticuloDto entity) {
        Optional<Articulo> articulo = Stream.of(entity)
                .map(articuloMapper)
                .findAny();
        articulo.ifPresent(articuloRepository :: save);
    }

    @Override
    public void addMegusta(ArticuloClienteDto articuloClienteDto) {
        Articulo articulo = articuloRepository.getReferenceById(articuloClienteDto.getId_articulo());
        Usuario usuario = usuarioRepository.getReferenceById(articuloClienteDto.getId_usuario());

        articulo.setMe_gusta(articulo.getMe_gusta() + 1);
        articuloRepository.save(articulo);

        ArticuloClienteDto articuloClienteDto1 = new ArticuloClienteDto();
        articuloClienteDto1.setId_articulo(articulo.getIdArticulo());
        articuloClienteDto1.setId_usuario(usuario.getUsuario());
        usuarioArticuloRepository.save(articuloClienteDto1);

    }

    @Override
    public List<ArticuloDto> getArticulosByCategoria(Long categoria) {
        List<Articulo> articuloDtos = articuloRepository.findAllByCategoria(categoria);
        return articuloDtos.stream().map(articuloDtoMapper).toList();
    }

    @Override
    public List<ArticuloDto> getArticulosByName(String name) {
        List<Articulo> articulos = articuloRepository.findArticuloByNombre(name);
        return articulos.stream().map(articuloDtoMapper).toList()
        ;
    }


}
