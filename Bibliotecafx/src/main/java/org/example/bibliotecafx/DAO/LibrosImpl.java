package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.Util.HibernateUtil;
import org.example.bibliotecafx.entities.Autores;
import org.example.bibliotecafx.entities.Libros;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class LibrosImpl implements LibrosDAO {

    // Agregar un nuevo libro
    @Override
    public boolean guardar(Libros libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Verificar si el autor está asignado, de lo contrario buscar el autor
            if (libro.getAutor() != null) {
                // Si el autor es un objeto nuevo, busca el autor en la base de datos si ya existe
                String nombreAutor = libro.getAutor().getNombre();
                Autores autorExistente = session.createQuery("FROM Autores WHERE nombre = :nombre", Autores.class)
                        .setParameter("nombre", nombreAutor)
                        .uniqueResult();
                if (autorExistente != null) {
                    libro.setAutor(autorExistente);  // Asignamos el autor existente a libro
                } else {
                    // Si el autor no existe, lo podemos guardar primero (si es necesario)
                    session.save(libro.getAutor());
                }
            }

            session.save(libro);  // Guardamos el libro en la base de datos
            transaction.commit();  // Hace commit para confirmar la operación
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, deshace los cambios
            }
            e.printStackTrace();
            return false;
        }
    }

    // Modificar un libro existente
    @Override
    public boolean modificar(Libros libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            // Verificar si el autor está asignado, de lo contrario buscar el autor
            if (libro.getAutor() != null) {
                // Si el autor es un objeto nuevo, busca el autor en la base de datos si ya existe
                String nombreAutor = libro.getAutor().getNombre();
                Autores autorExistente = session.createQuery("FROM Autores WHERE nombre = :nombre", Autores.class)
                        .setParameter("nombre", nombreAutor)
                        .uniqueResult();
                if (autorExistente != null) {
                    libro.setAutor(autorExistente);  // Asignamos el autor existente a libro
                } else {
                    // Si el autor no existe, lo podemos guardar primero (si es necesario)
                    session.save(libro.getAutor());
                }
            }

            session.update(libro);  // Actualiza el libro en la base de datos
            transaction.commit();  // Hace commit para confirmar la operación
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, deshace los cambios
            }
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar un libro por ISBN
    @Override
    public boolean eliminar(Libros libro) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Libros libroExistente = session.get(Libros.class, libro.getIsbn());
            if (libroExistente != null) {
                session.delete(libroExistente);
                transaction.commit();
                return true;
            } else {
                System.out.println("El libro con ISBN " + libro.getIsbn() + " no existe.");
                return false;
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();  // Si ocurre un error, deshace los cambios
            }
            e.printStackTrace();
            return false;
        }
    }

    // Buscar libros por título
    @Override
    public List<Libros> buscarPorTitulo(String titulo) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Libros l where l.titulo like :titulo", Libros.class)
                    .setParameter("titulo", "%" + titulo + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar libros por autor
    @Override
    public List<Libros> buscarPorAutor(String autor) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Libros l where l.autor.nombre like :autor", Libros.class)
                    .setParameter("autor", "%" + autor + "%")
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Buscar libros por ISBN
    @Override
    public List<Libros> buscarPorIsbn(String isbn) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            System.out.println("Buscando ISBN: " + isbn); // Depuración
            return session.createQuery("from Libros l where l.isbn = :isbn", Libros.class)
                    .setParameter("isbn", isbn)
                    .list(); // Retorna una lista
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }





    // Buscar libros por un término de búsqueda general
    @Override
    public List<Libros> buscar(String query) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Búsqueda por título o autor
            String hql = "from Libros l where l.titulo like :query or l.autor.nombre like :query";
            Query<Libros> queryResult = session.createQuery(hql, Libros.class)
                    .setParameter("query", "%" + query + "%");
            return queryResult.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Listar todos los libros disponibles
    @Override
    public List<Libros> listarLibrosDisponibles() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Libros WHERE prestado = false"; // Suponiendo que el campo 'prestado' exista en la entidad
            Query<Libros> query = session.createQuery(hql, Libros.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Listar todos los libros
    @Override
    public List<Libros> listarTodosLibros() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Libros", Libros.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
