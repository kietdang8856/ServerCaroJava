USE [caro]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 10/12/2023 4:01:45 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[username] [nvarchar](50) NOT NULL,
	[password] [nvarchar](50) NOT NULL,
	[win] [int] NULL,
	[lose] [int] NULL,
	[score] [int] NULL,
	[blocked] [bit] NULL
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Users] ON 
GO
INSERT [dbo].[Users] ([id], [username], [password], [win], [lose], [score], [blocked]) VALUES (10, N'tuankiet', N'admin', 12, 4, 800, 0)
GO
INSERT [dbo].[Users] ([id], [username], [password], [win], [lose], [score], [blocked]) VALUES (11, N'hieuliem', N'admin', 10, 5, 500, 1)
GO
INSERT [dbo].[Users] ([id], [username], [password], [win], [lose], [score], [blocked]) VALUES (16, N'liem', N'123456', NULL, NULL, NULL, 1)
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO
