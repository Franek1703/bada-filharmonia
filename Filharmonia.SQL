/*
Created: 30.11.2024
Modified: 01.12.2024
Project: Projekt BADA
Model: Filharmonia
Company: Politechnika Warszawska, EiTI
Author: Jakub Mądry, Franciszek Zarębski
Version: 1.0
Database: Oracle 19c
*/


-- Create tables section -------------------------------------------------

-- Table Sale_koncertowe

CREATE TABLE "Sale_koncertowe"(
  "Nr_sali_koncertowej" Integer NOT NULL,
  "Nazwa" Varchar2(100 ) NOT NULL,
  "Pojemnosc" Integer NOT NULL,
  "Typ_sali" Varchar2(100 ) NOT NULL,
  "Opis" Varchar2(200 ),
  "Liczba_wejsc" Integer NOT NULL,
  "Nr_filharmonii" Integer NOT NULL
)
/

-- Create indexes for table Sale_koncertowe

CREATE INDEX "IX_Filharmonia_posiada_sale" ON "Sale_koncertowe" ("Nr_filharmonii")
/

-- Add keys for table Sale_koncertowe

ALTER TABLE "Sale_koncertowe" ADD CONSTRAINT "Unique_Identifier1" PRIMARY KEY ("Nr_sali_koncertowej")
/

-- Table Wydarzenia

CREATE TABLE "Wydarzenia"(
  "Nr_wydarzenia" Integer NOT NULL,
  "Data" Date NOT NULL,
  "Nazwa" Varchar2(50 ) NOT NULL,
  "Opis" Varchar2(200 ),
  "Nr_sali_koncertowej" Integer NOT NULL
)
/

-- Create indexes for table Wydarzenia

CREATE INDEX "IX_Sala_odbywa_wydarzenie" ON "Wydarzenia" ("Nr_sali_koncertowej")
/

-- Add keys for table Wydarzenia

ALTER TABLE "Wydarzenia" ADD CONSTRAINT "Unique_Identifier2" PRIMARY KEY ("Nr_wydarzenia")
/

-- Table Bilety

CREATE TABLE "Bilety"(
  "Nr_biletu" Integer NOT NULL,
  "Data_zakupu" Date NOT NULL,
  "Cena_brutto" Number(10,2) NOT NULL,
  "Cena_netto" Number(10,2) NOT NULL,
  "Kategoria" Varchar2(100 ) NOT NULL,
  "Nr_klienta" Integer,
  "Nr_wydarzenia" Integer NOT NULL
)
/

-- Create indexes for table Bilety

CREATE INDEX "IX_Klient_zakupil_bilet" ON "Bilety" ("Nr_klienta")
/

CREATE INDEX "IX_Bilet_nalezy_do_wydarzenia" ON "Bilety" ("Nr_wydarzenia")
/

-- Add keys for table Bilety

ALTER TABLE "Bilety" ADD CONSTRAINT "Unique_Identifier3" PRIMARY KEY ("Nr_biletu")
/

-- Table Pracownicy

CREATE TABLE "Pracownicy"(
  "Nr_pracownika" Integer NOT NULL,
  "Imie" Varchar2(20 ) NOT NULL,
  "Nazwisko" Varchar2(30 ) NOT NULL,
  "Email" Varchar2(50 ) NOT NULL,
  "Stawka_brutto" Number(10,2) NOT NULL,
  "Data_zatrudnienia" Date NOT NULL,
  "Nr_filharmonii" Integer NOT NULL,
  "Nr_zespolu" Integer NOT NULL
)
/

-- Create indexes for table Pracownicy

CREATE INDEX "IX_Filharmonia_zatrudnia_pracownika" ON "Pracownicy" ("Nr_filharmonii")
/

CREATE INDEX "IX_Nalezy_do_zespolu" ON "Pracownicy" ("Nr_zespolu")
/

-- Add keys for table Pracownicy

ALTER TABLE "Pracownicy" ADD CONSTRAINT "Unique_Identifier4" PRIMARY KEY ("Nr_pracownika")
/

-- Table Klienci

CREATE TABLE "Klienci"(
  "Nr_klienta" Integer NOT NULL,
  "Imie" Varchar2(20 ) NOT NULL,
  "Nazwisko" Varchar2(30 ) NOT NULL,
  "Telefon" Varchar2(12 ) NOT NULL,
  "Nr_filharmonii" Integer NOT NULL
)
/

-- Create indexes for table Klienci

CREATE INDEX "IX_Jest_w_bazie_klientow" ON "Klienci" ("Nr_filharmonii")
/

-- Add keys for table Klienci

ALTER TABLE "Klienci" ADD CONSTRAINT "Unique_Identifier5" PRIMARY KEY ("Nr_klienta")
/

-- Table Filharmonie

CREATE TABLE "Filharmonie"(
  "Nr_filharmonii" Integer NOT NULL,
  "Nazwa" Varchar2(50 ) NOT NULL,
  "Adres" Varchar2(50 ) NOT NULL,
  "Telefon_kontaktowy" Varchar2(12 ) NOT NULL,
  "Email" Varchar2(50 ) NOT NULL,
  "www" Varchar2(50 )
)
/

-- Add keys for table Filharmonie

ALTER TABLE "Filharmonie" ADD CONSTRAINT "Unique_Identifier6" PRIMARY KEY ("Nr_filharmonii")
/

-- Table Kasjer

CREATE TABLE "Kasjer"(
  "Poziom_uprawnienia" Varchar2(100 ) NOT NULL,
  "Lokalizacja_kasy" Varchar2(100 ) NOT NULL,
  "Nr_pracownika" Integer NOT NULL
)
/

-- Add keys for table Kasjer

ALTER TABLE "Kasjer" ADD CONSTRAINT "Unique_Identifier7" PRIMARY KEY ("Nr_pracownika")
/

-- Table Wykonawca

CREATE TABLE "Wykonawca"(
  "Gatunek_muzyczny" Varchar2(30 ) NOT NULL,
  "Typ_artysty" Varchar2(30 ) NOT NULL,
  "Nr_pracownika" Integer NOT NULL
)
/

-- Add keys for table Wykonawca

ALTER TABLE "Wykonawca" ADD CONSTRAINT "Unique_Identifier8" PRIMARY KEY ("Nr_pracownika")
/

-- Table Zespoly

CREATE TABLE "Zespoly"(
  "Nr_zespolu" Integer NOT NULL,
  "Nazwa" Varchar2(50 ) NOT NULL,
  "Gatunek" Varchar2(50 ) NOT NULL,
  "Opis" Varchar2(200 ),
  "Nr_filharmonii" Integer NOT NULL
)
/

-- Create indexes for table Zespoly

CREATE INDEX "IX_Prowadzi_zespol" ON "Zespoly" ("Nr_filharmonii")
/

-- Add keys for table Zespoly

ALTER TABLE "Zespoly" ADD CONSTRAINT "Unique_Identifier9" PRIMARY KEY ("Nr_zespolu")
/

-- Table Sponsorzy

CREATE TABLE "Sponsorzy"(
  "Nr_sponsora" Integer NOT NULL,
  "Nazwa" Varchar2(30 ) NOT NULL,
  "Kwota_dotacji" Number(10,2) NOT NULL,
  "NIP" Char(10 ),
  "Nr_filharmonii" Integer NOT NULL
)
/

-- Create indexes for table Sponsorzy

CREATE INDEX "IX_Jest_w_bazie_sponsorow" ON "Sponsorzy" ("Nr_filharmonii")
/

-- Add keys for table Sponsorzy

ALTER TABLE "Sponsorzy" ADD CONSTRAINT "Unique_Identifier10" PRIMARY KEY ("Nr_sponsora")
/

-- Table Sponsorzy_na_wydarzeniach

CREATE TABLE "Sponsorzy_na_wydarzeniach"(
  "Nr_sponsora" Integer NOT NULL,
  "Nr_wydarzenia" Integer NOT NULL
)
/

-- Table Zespoly_na_wydarzeniach

CREATE TABLE "Zespoly_na_wydarzeniach"(
  "Nr_wydarzenia" Integer NOT NULL,
  "Nr_zespolu" Integer NOT NULL
)
/

-- Table Adresy

CREATE TABLE "Adresy"(
  "Nr_adresu" Integer NOT NULL,
  "Miasto" Char(30 ) NOT NULL,
  "Ulica" Char(30 ) NOT NULL,
  "Kod_pocztowy" Char(6 ) NOT NULL,
  "Numer_domu" Varchar2(10 ) NOT NULL,
  "Numer_mieszkania" Integer,
  "Nr_filharmonii" Integer NOT NULL,
  "Nr_sponsora" Integer
)
/

-- Create indexes for table Adresy

CREATE INDEX "IX_Filharmonia_ma_adres" ON "Adresy" ("Nr_filharmonii")
/

CREATE INDEX "IX_Sponsor_ma_adres" ON "Adresy" ("Nr_sponsora")
/

-- Add keys for table Adresy

ALTER TABLE "Adresy" ADD CONSTRAINT "PK_Adresy" PRIMARY KEY ("Nr_adresu")
/


-- Create foreign keys (relationships) section ------------------------------------------------- 

ALTER TABLE "Wydarzenia" ADD CONSTRAINT "Sala_odbywa_wydarzenie" FOREIGN KEY ("Nr_sali_koncertowej") REFERENCES "Sale_koncertowe" ("Nr_sali_koncertowej")
/



ALTER TABLE "Sale_koncertowe" ADD CONSTRAINT "Filharmonia_posiada_sale" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Pracownicy" ADD CONSTRAINT "Filharmonia_zatrudnia_pracownika" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Bilety" ADD CONSTRAINT "Klient_zakupil_bilet" FOREIGN KEY ("Nr_klienta") REFERENCES "Klienci" ("Nr_klienta")
/



ALTER TABLE "Pracownicy" ADD CONSTRAINT "Nalezy_do_zespolu" FOREIGN KEY ("Nr_zespolu") REFERENCES "Zespoly" ("Nr_zespolu")
/



ALTER TABLE "Zespoly" ADD CONSTRAINT "Filharmonia_prowadzi_zespol" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Sponsorzy" ADD CONSTRAINT "Jest_w_bazie_sponsorow" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Klienci" ADD CONSTRAINT "Jest_w_bazie_klientow" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Adresy" ADD CONSTRAINT "Filharmonia_ma_adres" FOREIGN KEY ("Nr_filharmonii") REFERENCES "Filharmonie" ("Nr_filharmonii")
/



ALTER TABLE "Adresy" ADD CONSTRAINT "Sponsor_ma_adres" FOREIGN KEY ("Nr_sponsora") REFERENCES "Sponsorzy" ("Nr_sponsora")
/



ALTER TABLE "Bilety" ADD CONSTRAINT "Bilet_nalezy_do_wydarzenia" FOREIGN KEY ("Nr_wydarzenia") REFERENCES "Wydarzenia" ("Nr_wydarzenia")
/





