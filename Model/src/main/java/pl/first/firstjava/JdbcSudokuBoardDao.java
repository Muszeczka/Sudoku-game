package pl.first.firstjava;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import static javax.persistence.Persistence.*;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable{
    private EntityManager em;

    public JdbcSudokuBoardDao(String unitName) {
        em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
    }

    @Override
    public SudokuBoard read() {
        return null;
    }

    public SudokuBoard readBoard(String name) {
        SudokuBoard board = em.createQuery("select b from SudokuBoard b where b.name = :name", SudokuBoard.class).setParameter("name", name).getSingleResult();
        remove(board);
        return board;
    }

    @Override
    public void write(SudokuBoard obj) throws IOException {
            try {
                em.getTransaction().begin();
                em.persist(obj);
                em.getTransaction().commit();
            }catch (Exception e) {
                throw new InvalidSave_LoadException("Nothing", e);
            }


    }

    public void remove(SudokuBoard obj) {
        em.getTransaction().begin();
        em.remove(obj);
        em.getTransaction().commit();
    }

    public List<SudokuBoard> findAll() {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<SudokuBoard> query = builder.createQuery(SudokuBoard.class);
        Root<SudokuBoard> studentRoot = query.from(SudokuBoard.class);
        query.select(studentRoot);
        TypedQuery<SudokuBoard> typedQuery = em.createQuery(query);
        System.out.println(typedQuery.getResultList().size());
        return typedQuery.getResultList();
    }

    public SudokuBoard findByName(String name) {
        SudokuBoard board = (pl.first.firstjava.SudokuBoard) em.createQuery("select b from SudokuBoard b where b.name = :name", SudokuBoard.class).setParameter("name", name).getSingleResult();
        return board;
    }


    @Override
    public void close() throws Exception {
        em.clear();
        em.close();
    }
}
