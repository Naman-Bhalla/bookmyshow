package com.bookmyshow.app.repositories.interfaces;

import com.bookmyshow.app.models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CinemaRepository extends JpaRepository<Cinema, Long> {
}


// How to actually switch form one db to another? Using another interface is great
// but the PostgreSQL datasource won't have the data that was there in MySQL datasource
// when we start using PostgreSQLRepository instead of MySQLRepository

// 1.) Actually migrating the DB
// SImplest Way: Have a down time. Have a migration script. Update the app servers.
// GTG
// Read replicas
// master - followers => replication log
// [... changes]
// creating a copy of the db at a particular instance (snapshot)
// migrate the db to a postgresql via some script
// cretae an intermediary server that reads replication log of mysql -> postgresql
// slowly slowly phase out mysql
// hld
// Support to fetch the status of the seats via a central cinema api
// implement an adapter for cinema apis
// implemenet the adapterinterface -> pvr and inox
// BookingController .book(List<ShowSeat>, Customer, Show)
// BookingService
//     1. Check with the cinema apis
//     2. mark the seats unavailable in cimnema api
//     3. create a booking
// save the booking object int he booking table (Booking repo)