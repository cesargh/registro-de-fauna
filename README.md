<a id="readme-inicio"></a>

<div align="center">

![java](https://img.shields.io/badge/Java-17.0.12-red)
![spring](https://img.shields.io/badge/Spring-3.3.4-red)
![hibernate](https://img.shields.io/badge/Hibernate-6.6.1-red)
![postgresql](https://img.shields.io/badge/PostgreSQL-16.4.2-red)
![intellij](https://img.shields.io/badge/IntelliJ-CE%202024.2.2-red)
![license](https://img.shields.io/badge/License-0BSD-brightgreen)
![update](https://img.shields.io/badge/Update-19%2FOct%2F2024-blue)
![version](https://img.shields.io/badge/Version-1.0.1-blue)
![stage](https://img.shields.io/badge/Stage-Release-blue)

</div>

<h1 align="center">Registro de Fauna</h1>
<div align="center"><img alt="Registro de Fauna" src="assets/logotipo.png" style="width:35%;height:35%;" /></div> 


<a id="readme-indice"></a>
## Índice de Contenidos:
---
- [Contexto](#contexto)
- [Guía de Usuario](#guía-de-usuario)
- [Guía de Instalación](#guía-de-instalación)
- [Autor](#autor)
- [Licencia](#licencia)
- [Agradecimientos](#agradecimientos)
- [Documentos Complementarios](#documentos-complementarios)


## Contexto
---
Registro de Fauna es una aplicación desarrollada en Java con el entorno IntelliJ para el curso &quot;Java Persistencia de Datos y Consultas con Spring Data JPA&quot; de ONE + Alura Latam. Ha sido manufacturada por su autor (sin ayuda de IA) en Octubre de 2024.

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>

## Guía de Usuario
---
Esta aplicación permite registrar avistamientos de animales y luego consultar diferentes informes según los datos almacenados. Para ello, persiste en una base de datos relacional la información suministrada utilizando la tecnología aprendida en el curso.

El modelo de datos es muy sencillo: Se trata de una relación <code>@ManyToMany</code> entre las entidades <code>Animal</code> y <code>Pais</code>.

Para asegurar la consistencia al momento de guardar, se utilizan transacciones de modo manual en los bloques <code>try-catch-finally</code>.

A continuación, unas muestras de su interfaz:

<table align="center" style="border:0">
<tr>
<td align="center"><img src="assets/screenshot1.jpg" /></td>
<td align="center"><img src="assets/screenshot2.jpg" style="width:60%;height:60%;" /></td>
</tr>
<tr>
<td align="center"><img src="assets/screenshot3.jpg" style="width:60%;height:60%;" /></td>
<td align="center"><img src="assets/screenshot4.jpg" /></td>
</tr>
<tr>
<td align="center" colspan="2"><img src="assets/screenshot5.jpg" style="width:80%;height:80%;" /></td>
</tr>
</table>

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>

## Guía de Instalación
---
Simplemente se requiere clonar este proyecto en el repositorio local, y luego ejecutarlo con un IDE acorde (como IntelliJ, Eclipse, etc) desde el método <code>main</code> de la clase <code>RegistroDeFaunaApplication</code>.

Dado que la aplicación accede a base de datos, es importante asegurar que exista un servidor PostgreSQL 16.4.2 o superior previamente instalado, y que tanto antivirus como cortafuegos no impidan dicho comportamiento.

Importante: No olvidar declarar las variables de entorno que se utilizan en <code>application.properties</code>.

## Autor
---
César es alumno de Alura. Inició su formación en Junio de 2024 dentro del plan Oracle Next Education para Latinoamérica. Tiene 54 años y es nacido en Buenos Aires.

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>

## Licencia 
---
Licencia BSD Zero Clause (0BSD) detallada en <code>LICENSE.txt</code> en la raíz del repositorio. Más información en https://opensource.org/license/0bsd

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>

## Agradecimientos 
---
* Generador de logotipo : https://logomakerr.ai/
* Generador de favicon : https://favicon.io/

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>

## Documentos Complementarios
---
* Clonar un repositorio de GitHub : https://docs.github.com/es/repositories/creating-and-managing-repositories/cloning-a-repository
* Scripts SQL de base de datos :  https://github.com/cesargh/registro-de-fauna/tree/master/sql
* Sito Web Oficial de PostgreSQL : https://www.postgresql.org

<div align="right">&#8593; <a href="#readme-indice">Índice</a> &#8593; <a href="#readme-inicio">Inicio</a> &#8593;</div>
