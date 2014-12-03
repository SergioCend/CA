/* ========================================================================
 * Copyright 2014 EscherFinal
 *
 * Licensed under the MIT, The MIT License (MIT)
 * Copyright (c) 2014 EscherFinal

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 * ========================================================================


Source generated by CrudMaker version 1.0.0.201410152247

*/

package co.edu.uniandes.csw.EscherFinal.usuario.master.logic.ejb;

import co.edu.uniandes.csw.EscherFinal.trabajo.logic.dto.TrabajoDTO;
import co.edu.uniandes.csw.EscherFinal.trabajo.persistence.api.ITrabajoPersistence;
import co.edu.uniandes.csw.EscherFinal.usuario.logic.dto.UsuarioDTO;
import co.edu.uniandes.csw.EscherFinal.usuario.master.logic.api._IUsuarioMasterLogicService;
import co.edu.uniandes.csw.EscherFinal.usuario.master.logic.dto.UsuarioMasterDTO;
import co.edu.uniandes.csw.EscherFinal.usuario.master.persistence.api.IUsuarioMasterPersistence;
import co.edu.uniandes.csw.EscherFinal.usuario.master.persistence.entity.UsuariotrabajoEntity;
import co.edu.uniandes.csw.EscherFinal.usuario.persistence.api.IUsuarioPersistence;
import javax.inject.Inject;

public abstract class _UsuarioMasterLogicService implements _IUsuarioMasterLogicService {

    @Inject
    protected IUsuarioPersistence usuarioPersistance;
    @Inject
    protected IUsuarioMasterPersistence usuarioMasterPersistance;
    @Inject
    protected ITrabajoPersistence trabajoPersistance;

    public UsuarioMasterDTO createMasterUsuario(UsuarioMasterDTO usuario) {
        UsuarioDTO persistedUsuarioDTO = usuarioPersistance.createUsuario(usuario.getUsuarioEntity());
        if (usuario.getCreatetrabajo() != null) {
            for (TrabajoDTO trabajoDTO : usuario.getCreatetrabajo()) {
                TrabajoDTO createdTrabajoDTO = trabajoPersistance.createTrabajo(trabajoDTO);
                UsuariotrabajoEntity usuarioTrabajoEntity = new UsuariotrabajoEntity(persistedUsuarioDTO.getId(), createdTrabajoDTO.getId());
                usuarioMasterPersistance.createUsuariotrabajoEntity(usuarioTrabajoEntity);
            }
        }
        // update trabajo
        if (usuario.getUpdatetrabajo() != null) {
            for (TrabajoDTO trabajoDTO : usuario.getUpdatetrabajo()) {
                trabajoPersistance.updateTrabajo(trabajoDTO);
                UsuariotrabajoEntity usuarioTrabajoEntity = new UsuariotrabajoEntity(persistedUsuarioDTO.getId(), trabajoDTO.getId());
                usuarioMasterPersistance.createUsuariotrabajoEntity(usuarioTrabajoEntity);
            }
        }
        return usuario;
    }

    public UsuarioMasterDTO getMasterUsuario(Long id) {
        return usuarioMasterPersistance.getUsuario(id);
    }

    public void deleteMasterUsuario(Long id) {
        usuarioPersistance.deleteUsuario(id);
    }

    public void updateMasterUsuario(UsuarioMasterDTO usuario) {
        usuarioPersistance.updateUsuario(usuario.getUsuarioEntity());

        //---- FOR RELATIONSHIP
        // persist new trabajo
        if (usuario.getCreatetrabajo() != null) {
            for (TrabajoDTO trabajoDTO : usuario.getCreatetrabajo()) {
                TrabajoDTO createdTrabajoDTO = trabajoPersistance.createTrabajo(trabajoDTO);
                UsuariotrabajoEntity usuarioTrabajoEntity = new UsuariotrabajoEntity(usuario.getUsuarioEntity().getId(), createdTrabajoDTO.getId());
                usuarioMasterPersistance.createUsuariotrabajoEntity(usuarioTrabajoEntity);
            }
        }
        // update trabajo
        if (usuario.getUpdatetrabajo() != null) {
            for (TrabajoDTO trabajoDTO : usuario.getUpdatetrabajo()) {
                trabajoPersistance.updateTrabajo(trabajoDTO);
            }
        }
        // delete trabajo
        if (usuario.getDeletetrabajo() != null) {
            for (TrabajoDTO trabajoDTO : usuario.getDeletetrabajo()) {
                usuarioMasterPersistance.deleteUsuariotrabajoEntity(usuario.getUsuarioEntity().getId(), trabajoDTO.getId());
                trabajoPersistance.deleteTrabajo(trabajoDTO.getId());
            }
        }
    }
}
