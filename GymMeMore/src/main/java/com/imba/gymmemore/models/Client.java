package com.imba.gymmemore.models;

import java.time.LocalDate;

public class Client extends Person{
    private LocalDate joinDate;
    private int classesCount;
    private ProfLevel profLevel;
    private BankAccount bankAccount;
}
