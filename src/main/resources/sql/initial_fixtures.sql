insert into subject_type(label, name) values
('unknown','Unknown Subject Type'),
('lsoa', 'Lower Layer Super Output Area'),
('msoa', 'Middle Layer Super Output Area'),
('localAuthority', 'Local Authority'),
('sensor', 'Sensor'),
('poi', 'Point of interest');

insert into provider(label, name) values
('uk.org.tombolo.test', 'Tombolo Tester');

insert into attribute(provider_label, label, name, description) values
('uk.org.tombolo.test', 'testAttribute', 'Tombolo Test Attribute', 'Attribute used in testing of the Tombolo Digital Connector');