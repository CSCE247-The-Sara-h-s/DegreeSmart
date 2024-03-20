
# Dot source this file:	. .\BuildTools.ps1

function Build-Java {
	[CmdletBinding()]
	param(
		[Parameter( Mandatory = $True )]
		[String]
		$Folder
	)

	process {
		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', $Folder )
		$Src = [IO.Path]::Combine( $PSScriptRoot, '..', 'src', $Folder, 'java' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$Package = 'degreesmart'

		[void]( Remove-Item -Path $Bin -Recurse -Force -ErrorAction 'SilentlyContinue' )
		[void]( New-Item -Path $Bin -Type 'Directory' -Force -ErrorAction 'Stop' )

		$Files = Get-ChildItem ( Join-Path $Src $Package ) |
			Where-Object Name -like '*.java'

		$FileNames = foreach ( $File in $Files ) {
			Write-Output "${Src}/${Package}/$($File.name)"
		}

		try {
			javac -Xlint:unchecked -d $Bin -cp "${Lib};${Src}" $FileNames
			Write-Verbose -Verbose "Build finished: $Src"
		} catch {
			Write-Error "Build failed: $($Error[0])"
		}
	}
}

function Invoke-DegreeSmart {
	[CmdletBinding()]
	param( )

	process {
		Build-Java -Folder 'main'

		$Bin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'main' )
		$Src = [IO.Path]::Combine( $PSScriptRoot, '..', 'src', 'main', 'java' )
		$Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$Package = 'degreesmart'
		$Main = 'DegreeSmartUI'

		java -cp "${Bin};${Lib};${Src}" "${Package}.${Main}"
	}
}

function Invoke-Test {
	[CmdletBinding()]
	param( )

	process {
		Build-Java -Folder 'main' -ErrorAction 'Stop'
		Build-Java -Folder 'test' -ErrorAction 'Stop'

		$MainBin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'main' )
		$TestBin = [IO.Path]::Combine( $PSScriptRoot, '..', 'bin', 'test' )
		    $Lib = [IO.Path]::Combine( $PSScriptRoot, '..', 'lib', '*' )
		$JUnit = 'org.junit.platform.console.ConsoleLauncher'

		java -cp "${MainBin};${TestBin};${Lib}" $JUnit execute --scan-classpath #|
			#Tee-Object -Variable JUnitResult

		# [Regex]$Regex = "\s*\[\s*(?<fail>\d+)\s*tests\s*failed\s*\]\s*"
		# $Match = $Regex.match( $JUnitResult )

		# if ( $Match.Groups["fail"].Value -ne "0" ) {
		# 	[Console]::ForegroundColor = "Red"
		# 	[Console]::Error.WriteLine("ERROR: $( $Match.Value -replace '\s\s+|[\[\]]','' )")
		# 	[Console]::ResetColor()
		# }
	}
}
