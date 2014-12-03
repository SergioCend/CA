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

package co.edu.uniandes.csw.EscherFinal.imagen.logic.ejb;
import java.util.List;
import javax.inject.Inject;

import co.edu.uniandes.csw.EscherFinal.imagen.logic.dto.ImagenDTO;
import co.edu.uniandes.csw.EscherFinal.imagen.logic.dto.ImagenPageDTO;
import co.edu.uniandes.csw.EscherFinal.imagen.logic.api._IImagenLogicService;
import co.edu.uniandes.csw.EscherFinal.imagen.persistence.api.IImagenPersistence;

public abstract class _ImagenLogicService implements _IImagenLogicService {

	@Inject
	protected IImagenPersistence persistance;

	public ImagenDTO createImagen(ImagenDTO imagen){
		return persistance.createImagen( imagen); 
    }

	public List<ImagenDTO> getImagens(){
		return persistance.getImagens(); 
	}

	public ImagenPageDTO getImagens(Integer page, Integer maxRecords){
		return persistance.getImagens(page, maxRecords); 
	}

	public ImagenDTO getImagen(Long id){
		return persistance.getImagen(id); 
	}

	public void deleteImagen(Long id){
	    persistance.deleteImagen(id); 
	}

	public void updateImagen(ImagenDTO imagen){
	    persistance.updateImagen(imagen); 
	}	
}