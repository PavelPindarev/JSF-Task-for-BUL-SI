package bg.bulsi.util;

import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PhaseListenerImpl implements PhaseListener {

    @Inject
    private SessionFactory sessionFactory;

    //Before
    @Override
    public void beforePhase(PhaseEvent event) {
        System.out.println("Before " + event.getPhaseId());
        if (event.getPhaseId().equals(PhaseId.RESTORE_VIEW)) {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            FacesContextUtil.setRequestSession(session);
        }
    }

    //After
    @Override
    public void afterPhase(PhaseEvent event) {
        System.out.println("After: " + event.getPhaseId());
        if (event.getPhaseId().equals(PhaseId.RENDER_RESPONSE)) {
            Session session = FacesContextUtil.getRequestSession();
            try {
                session.getTransaction().commit();
            } catch (Exception e) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
            } finally {
                session.close();
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.ANY_PHASE;
    }

}