package com.afip.afip;


import com.afip.afip.dominio.Impuesto;
import com.afip.afip.dominio.Usuario;
import com.afip.afip.repository.ImpuestoRepository;
import com.afip.afip.repository.UsuarioRepository;
import com.afip.afip.service.AfipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class AfipController {

    @Autowired
    ImpuestoRepository impuestorepository;


    @Autowired
    UsuarioRepository usuariorepository;

    Impuesto impuestoClass;
    AfipService service;
    Usuario usuario;

    @RequestMapping(value = "/impuestos", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getAllImpuestos(){
        try{
            List<Impuesto> impList =  impuestorepository.findAll();
            return new ResponseEntity<>(impList, HttpStatus.OK);

        }catch (Exception e){

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping (value = "/impuestos/nombre/{nombreImpuesto}",method = RequestMethod.GET)
    @ResponseBody
    public Impuesto nombreImpuesto(@PathVariable String nombreImpuesto){
        try{
            return impuestorepository.findAllByNombreLike(nombreImpuesto);

        } catch (Exception e){
            return null;

        }
    }
    @RequestMapping (value = "/impuestos/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Impuesto getIdImpuesto(@PathVariable Long id){
        try{
            return impuestorepository.findByIdimpuestoLike(id);

        } catch (Exception e){
            return null;

        }
    }
    @GetMapping ("/usuario/clave/{clavefiscal}")
    public Usuario claveFiscal(@PathVariable Long clavefiscal){
        return usuariorepository.findByClavefiscalLike(clavefiscal);
    }

    /*
    @RequestMapping (value = "/usuario/clave/{clavefiscal}",method = RequestMethod.GET)
    @ResponseBody
    public Usuario getClaveFiscal(@PathVariable Long clavefiscal){
        try{
            return usuariorepository.findByClavefiscalLike(clavefiscal);

        } catch (Exception e){
            return null;
        }
    }
*/
    @RequestMapping (value = "impuestos/delete/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Impuesto> deleteImpuesto(@PathVariable Long id){
        try{

            Optional<Impuesto> imp = impuestorepository.findById(id);
            if ( imp.isPresent() ) {
                impuestorepository.delete(imp.get());
                return new ResponseEntity<>(null,HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/impuestos/relacion/{clave_fiscal}/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public Usuario postRelacion(@PathVariable Long clave_fiscal, @PathVariable Long id){

            Usuario usuario = this.claveFiscal(clave_fiscal);
            Impuesto impuesto = this.getIdImpuesto(id);

            usuario.getUsuimp().add(impuesto);
            return usuariorepository.save(usuario);


    }

@RequestMapping(value = "/impuestos/{nombreImpuesto}/{fecha}", method = RequestMethod.PUT)
@ResponseBody
public ResponseEntity<Impuesto> actFechaLiquidacion(@PathVariable String nombreImpuesto, @PathVariable Date fecha){
    try {
        Impuesto imp = this.nombreImpuesto(nombreImpuesto);
        imp.setFechaliquidacion(fecha);
        return new ResponseEntity<>(impuestorepository.save(imp),HttpStatus.OK);
    }catch (Exception e ){
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    }

    @RequestMapping(value = "/impuestos/{fechadesde}/{fechahasta}",method = RequestMethod.GET)
    @ResponseBody
    public List<Impuesto> fechasBetween(@PathVariable Date fechadesde, @PathVariable Date fechahasta){
        try{

            return impuestorepository.findByFechaliquidacionBetween(fechadesde,fechahasta);

        }catch (Exception e){
            return null;
        }
    }
    /*
    @RequestMapping(value = "/impuestos/{fechadesde}/{fechahasta}",method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Impuesto> fechasBetween(@PathVariable Date fechadesde, @PathVariable Date fechahasta){
        try{

            return new ResponseEntity<>(impuestorepository.findByFechaliquidacionBetween(fechadesde,fechahasta),HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
     */

    //Metodo para listar impuestos por numero fiscal
    @RequestMapping(value="/usuario/lista/{claveFiscal}", method=RequestMethod.GET)
    public List<Impuesto> getUsuarioClaveTipo(@PathVariable("claveFiscal") Long claveFiscal){
        Usuario us=this.claveFiscal(claveFiscal);
        return us.getUsuimp();

    }



}
