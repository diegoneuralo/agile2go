//package br.com.scrum.test.user;
//
//import org.jboss.shrinkwrap.api.spec.WebArchive;
//
//import br.com.scrum.domain.entity.User;
//import br.com.scrum.domain.entity.enums.UserRole;
//import br.com.scrum.domain.service.UserService;
//import br.com.scrum.infrastructure.dao.PersistenceUtil;
//import br.com.scrum.test.Agile2GoDeployment;
//
//public class UserDeployment
//{
//	public static WebArchive deployment()
//	{
//		return Agile2GoDeployment.deployment().
//				addClass(User.class).
//				addClass(PersistenceUtil.class).
//				addClass(UserService.class).
//				addClass(UserRole.class);
//	}
//}
