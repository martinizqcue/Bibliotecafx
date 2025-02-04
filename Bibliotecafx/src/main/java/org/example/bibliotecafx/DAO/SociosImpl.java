package org.example.bibliotecafx.DAO;

import org.example.bibliotecafx.entities.Socios;
import org.example.bibliotecafx.Util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SociosImpl implements SociosDAO {

    // Método para agregar un nuevo socio
    @Override
    public void agregarSocio(Socios socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(socio);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para modificar un socio
    @Override
    public void modificarSocio(Socios socio) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(socio);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un socio
    @Override
    public void eliminarSocio(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            Socios socio = session.get(Socios.class, id);
            if (socio != null) {
                session.delete(socio);
                transaction.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para buscar un socio por nombre
    @Override
    public Socios buscarSocioPorNombre(String nombre) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios WHERE nombre = :nombre", Socios.class)
                    .setParameter("nombre", nombre)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para buscar un socio por número de teléfono
    @Override
    public Socios buscarSocioPorTelefono(Integer telefono) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios WHERE telefono = :telefono", Socios.class)
                    .setParameter("telefono", telefono)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Método para listar todos los socios
    @Override
    public List<Socios> listarSocios() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Socios", Socios.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
