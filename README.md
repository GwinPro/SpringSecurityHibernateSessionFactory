# SpringSecurityHibernateSessionFactory

Изменения:
1. Переделал с EntityManagerFactory на sessionFactory;
2. Убрал в аннотации FetchType.EAGER (FetchType.LAZY стоит по умолчанию);
3. Добавил JOIN FETCH в запросах;
4. Переименовал UserDetailService в SecurityUserDetailService и переместил в папку security;
5. Разбил UserController на множество контроллеров и разложил по папкам;
6. Имплементацию DAO вынес в отдельную папку daoImpl;
7. Имплементацию Service вынес в отдельную папку ServiceImpl.

