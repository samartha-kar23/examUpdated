package com.kar;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class QuestionsUtility {
	Session session = null;
	SessionFactory factory;
	List<Object[]> list = null;
	HashMap<Integer, String> response=new HashMap<>();
	int currentQuestion = 0;
	
	public void Category(String Exam_Id){
	try{
			factory=new Configuration().configure().buildSessionFactory();
			session=factory.openSession();
			Transaction tr=session.beginTransaction();
			Query query=session.createQuery("select q.id,q.Question, q.option_1,q.option_2,q.option_3,q.option_4,q.option_crt "
					+ "from Questions q "
					+ "where exam_id like :Exam_Id").setParameter("Exam_Id", Exam_Id);
			list=query.list();
				
//				Iterator iter=list.iterator();
//				while(iter.hasNext())
//					{
//					System.out.println(iter.next());
//					}
				tr.commit();

			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally
			{
				session.flush();
				session.close();
			}
		
			
			//return list;

			
		}
	public int NumberOfQuestions() {
			
		return list.size(); 
	}
	
	public Object[] question(int currentQ) {
		
		return list.get(currentQ);
		
		
	}
	public Map<Integer, String> saveResponse(String option, int currentQ) {
		System.out.println(option+ " " + currentQ );
		response.put(currentQ,option);
		System.out.println(response);
		return response;
	}
	
	public String getResponse( int currentQ) {
		System.out.println("This question is " + currentQ );
		response.get(currentQ);
		System.out.println(response.get(currentQ));
		return response.get(currentQ);
	}

	}


