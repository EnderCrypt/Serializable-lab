package com.github.serializable;

import com.github.serializable.collection.file.FileOrderRepository;
import com.github.serializable.collection.file.FileProductRepository;
import com.github.serializable.collection.file.FileUserRepository;

public class Main
{

	public static void main(String[] args)
	{
		// create file repositories
		FileUserRepository userRep = new FileUserRepository("Repository/User/");
		FileOrderRepository orderRep = new FileOrderRepository("Repository/Order/");
		FileProductRepository productRep = new FileProductRepository("Repository/Product/");
	}

}
